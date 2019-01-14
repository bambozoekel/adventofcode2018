package moerk.device.debugger;

import moerk.device.Instruction;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author matthias
 */
public class SwingDebuggerUI implements DebuggerUI {
	private JFileChooser fileChooser;
	private final Debugger debugger;
	private final IpCellRenderer ipCellRenderer;
	private final BreakpointCellRenderer breakPointCellRenderer;

	private final JFrame frame;
	private final JTable instructionTable;
	private final List<JTextField> registers;

	private InstructionTableModel tableModel;

	public SwingDebuggerUI() {
		debugger = new Debugger( this );

		JButton open = new JButton( new OpenIcon() );
		open.addActionListener( event -> debugger.open() );

		JButton step = new JButton( new StepIcon() );
		step.addActionListener( event -> debugger.step() );

		JButton run = new JButton( new RunIcon() );
		run.addActionListener( event -> debugger.run() );

		JToolBar toolbar = new JToolBar();
		toolbar.add( open );
		toolbar.addSeparator();;
		toolbar.add( step );
		toolbar.add( run );

		ipCellRenderer = new IpCellRenderer();
		breakPointCellRenderer = new BreakpointCellRenderer();

		tableModel = new InstructionTableModel();
		instructionTable = new JTable( tableModel );
		instructionTable.getColumnModel().getColumn( 0 ).setMaxWidth( 25 );
		instructionTable.getColumnModel().getColumn( 0 ).setCellRenderer( ipCellRenderer );
		instructionTable.getColumnModel().getColumn( 1 ).setMaxWidth( 25 );
		instructionTable.getColumnModel().getColumn( 1 ).setCellRenderer( breakPointCellRenderer );
		instructionTable.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent e ) {
				toggleBreakpoint( e );
			}
		} );

		JScrollPane instructionScroller = new JScrollPane( instructionTable );
		instructionScroller.setBorder( BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Instructions" ) );

		registers = new ArrayList<>();
		Component registerPanel = createRegisterPanel();

		frame = new JFrame( "Elve Assembler Debugger" );
		frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
		frame.getContentPane().setLayout( new BorderLayout() );

		frame.getContentPane().add( toolbar, BorderLayout.NORTH );
		frame.getContentPane().add( instructionScroller, BorderLayout.CENTER );
		frame.getContentPane().add( registerPanel, BorderLayout.EAST );
		frame.pack();

		frame.setVisible( true );
	}

	private void toggleBreakpoint( MouseEvent e ) {
		if ( e.getClickCount() == 1 ) {
			int column = instructionTable.getSelectedColumn();
			if ( column == 1 ) {
				debugger.toggleBreakpoint( instructionTable.getSelectedRow() );
			}
		}
	}

	private Component createRegisterPanel() {
		JPanel registerPanel = new JPanel();
		registerPanel.setLayout( new GridBagLayout() );

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets( 0, 5, 5, 5 );
		c.anchor = GridBagConstraints.EAST;

		for ( int i = 0; i < 6; i++ ) {
			JTextField register = new JTextField( 10 );
			register.setEditable( false );

			c.gridx = 0;
			c.gridy = i;

			registerPanel.add( new JLabel( Integer.toString( i ) ), c );

			c.gridx = 1;
			registerPanel.add( register, c );
			registers.add( register );
		}

		c = new GridBagConstraints();
		c.gridy = registers.size();
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		registerPanel.add( new JPanel(), c );
		registerPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Registers" ) );

		return registerPanel;
	}

	@Override
	public void showError( String errorMessage ) {
		JOptionPane.showMessageDialog( frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE );
	}

	@Override
	public Optional<File> selectFile() {
		if ( fileChooser == null ) {
			fileChooser = new JFileChooser();
		}

		if ( fileChooser.showOpenDialog( frame ) == JFileChooser.APPROVE_OPTION ) {
			return Optional.of( fileChooser.getSelectedFile() );
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public void showInstructions( List<Instruction> instructions ) {
		tableModel.setInstructions( instructions );
	}

	@Override
	public void markIpRegister( int ipRegisterIndex ) {
		registers.get( ipRegisterIndex ).setBorder( BorderFactory.createLineBorder( Color.RED ) );
	}

	@Override
	public void markInstructionPointer( int instructionPointer ) {
		ipCellRenderer.instructionPointer = instructionPointer;
		instructionTable.repaint();
	}

	@Override
	public void setRegisterValue( int index, int value ) {
		registers.get( index ).setText( Integer.toString( value ) );
	}

	@Override
	public void markBreakpoint( int line ) {
		breakPointCellRenderer.breakPoints.add( line );
	}

	@Override
	public void removeBreakpoint( int line ) {
		breakPointCellRenderer.breakPoints.remove( line );
	}

	private static class IpCellRenderer implements TableCellRenderer {
		private static final Icon BALL = new BallIcon();

		private TableCellRenderer delegate = new DefaultTableCellRenderer();
		private int instructionPointer = -1;

		@Override
		public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ) {
			JLabel label = (JLabel)delegate.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );

			if ( row == instructionPointer ) {
				label.setIcon( BALL );
			}
			else {
				label.setIcon( null );
			}

			return label;
		}
	}

	private static final class BreakpointCellRenderer implements TableCellRenderer {
		private TableCellRenderer delegate = new DefaultTableCellRenderer();
		private Set<Integer> breakPoints = new HashSet<>();

		@Override
		public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ) {
			JLabel label = (JLabel)delegate.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
			if ( breakPoints.contains(row)) {
				label.setBackground( Color.RED );
			}
			else {
				label.setBackground( table.getBackground() );
			}

			return label;
		}
	}

	private static class BallIcon implements Icon {
		@Override
		public void paintIcon( Component c, Graphics g, int x, int y ) {
			g.setColor( Color.RED );
			g.fillOval( x, y, 15, 15 );
		}

		@Override
		public int getIconWidth() {
			return 15;
		}

		@Override
		public int getIconHeight() {
			return 15;
		}
	}

	private static class OpenIcon implements Icon {

		@Override
		public void paintIcon( Component c, Graphics g, int x, int y ) {
			g.setColor( Color.BLACK );
			g.drawLine( x, y, x + 19, y );
			g.drawLine( x, y, x, y + 24 );
			g.drawLine( x + 19, y, x + 19, y + 5 );

			g.drawLine( x + 5, y + 5, x + 24, y + 5 );
			g.drawLine( x + 5, y + 5, x, y + 24 );
			g.drawLine( x + 24, y + 5, x + 19, y + 24 );

			g.drawLine( x, y + 24, x + 19, y + 24 );
		}

		@Override
		public int getIconWidth() {
			return 25;
		}

		@Override
		public int getIconHeight() {
			return 25;
		}
	}

	private static class StepIcon implements Icon {

		@Override
		public void paintIcon( Component c, Graphics g, int x, int y ) {
			int[] xs = { x + 2, x + 23, x + 2 };
			int[] ys = { y + 2, y + 10, y + 23 };

			g.setColor( Color.BLUE );
			g.fillPolygon( xs, ys, 3 );
		}

		@Override
		public int getIconWidth() {
			return 25;
		}

		@Override
		public int getIconHeight() {
			return 25;
		}
	}

	private static class RunIcon implements Icon {

		@Override
		public void paintIcon( Component c, Graphics g, int x, int y ) {
			int[] xs = { x + 9, x + 22, x + 9 };
			int[] ys = { y + 2, y + 10, y + 22 };

			g.setColor( Color.RED );
			g.fillRect( x + 2, y + 2, 5, 22 );
			g.fillPolygon( xs, ys, 3 );
		}

		@Override
		public int getIconWidth() {
			return 25;
		}

		@Override
		public int getIconHeight() {
			return 25;
		}
	}

	public static void main( String[] args ) {
		SwingUtilities.invokeLater( SwingDebuggerUI::new );
	}
}
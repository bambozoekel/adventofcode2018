package moerk.day10.puzzle1;

import moerk.day10.Star;
import moerk.day10.StarReader;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author matthias
 */
public class SkyMessage {


	public static void main( String[] args ) {
		final List<Star> stars = new StarReader().read();
		int size1 = Integer.MAX_VALUE;
		int size2 = size( stars );
		int s = 0;

		while ( size2 < size1 ) {
			stars.forEach( Star::moveForward );
			size1 = size2;
			size2 = size(stars);
			s++;
		}

		System.out.println(s - 1 + " " + size1);
	}

	private static int size( List<Star> stars ) {
		int minx = stars.stream()
			.map( Star::getX )
			.min( Comparator.naturalOrder() )
			.orElseThrow();

		int miny = stars.stream()
			.map( Star::getY )
			.min( Comparator.naturalOrder() )
			.orElseThrow();

		int maxx = stars.stream()
			.map( Star::getX )
			.max( Comparator.naturalOrder() )
			.orElseThrow();

		int maxy = stars.stream()
			.map( Star::getY )
			.max( Comparator.naturalOrder() )
			.orElseThrow();

		return (maxx - minx) * 2 + (maxy - miny) * 2;
	}

	private static void blerk() {
		List<Star> stars = new ArrayList<>();

		int i = 10139;
		stars.forEach(s -> s.jumpTo( i ));

//		for ( int i = 0; i < 10200; i++ ) {
			int minx = stars.stream()
				.map( Star::getX )
				.min( Comparator.naturalOrder() )
				.orElseThrow();

			int miny = stars.stream()
				.map( Star::getY )
				.min( Comparator.naturalOrder() )
				.orElseThrow();

			int maxx = stars.stream()
				.map( Star::getX )
				.max( Comparator.naturalOrder() )
				.orElseThrow();

			int maxy = stars.stream()
				.map( Star::getY )
				.max( Comparator.naturalOrder() )
				.orElseThrow();

			//System.out.println( ( );
			System.out.println( String.format( "%04d", i ) + " " + minx + "x" + miny + "-" + maxx + "x" + maxy + " (" + ((maxx - minx) * (maxy - miny)) + ")");
//			stars.forEach( Star::moveForward );
//		}

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize( 1000, 1000 );
		Icon icon = new Icon() {
			@Override
			public void paintIcon( Component c, Graphics g, int x, int y ) {
				g.setColor( Color.RED );
				stars.forEach( s -> g.fillRect(3*s.getX(), 3*s.getY(), 3, 3));
			}

			@Override
			public int getIconWidth() {
				return 900;
			}

			@Override
			public int getIconHeight() {
				return 900;
			}
		};

		frame.getContentPane().add( new JLabel( icon ) );
		frame.setVisible(true);

		final Timer timer = new Timer( 500, e -> {stars.forEach(Star::moveForward); frame.getContentPane().repaint();} );
		JButton back = new JButton("<<" );
		back.addActionListener( e -> { stars.forEach( Star::moveBackward ); frame.getContentPane().repaint(); } );

		JButton pause = new JButton("||" );
		pause.addActionListener( e -> { if ( timer.isRunning() ) timer.stop(); else timer.start(); } );

		JButton forward = new JButton(">>" );
		forward.addActionListener( e -> { stars.forEach( Star::moveForward ); frame.getContentPane().repaint(); } );

		JButton explain = new JButton("T");
		explain.addActionListener( e -> System.out.println(stars.get(0).getT()) );

		Box box = Box.createHorizontalBox();
		box.add(back);
		box.add(pause);
		box.add(forward);
		box.add(explain);
		frame.getContentPane().add(box, BorderLayout.SOUTH);
	}
}
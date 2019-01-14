package moerk.device.debugger;

import moerk.device.Instruction;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author matthias
 */
class InstructionTableModel extends AbstractTableModel {
	private static final List<String> COLUMN_NAMES = List.of(
		"IP",
		"#",
		"Opcode",
		"Input A",
		"Input B",
		"Output"
	);

	private ArrayList<Instruction> instructions;

	void setInstructions( List<Instruction> instructions ) {
		this.instructions = new ArrayList<>( instructions );
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		if ( instructions == null ) {
			return 0;
		}
		else {
			return instructions.size();
		}
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public Object getValueAt( int rowIndex, int columnIndex ) {
		if ( columnIndex == 0 ) {
			return null;
		}
		else if ( columnIndex == 1 ) {
			return rowIndex;
		}
		else if ( columnIndex == 2 ) {
			return instructions.get( rowIndex ).getOperation();
		}
		else if ( columnIndex >= 3 && columnIndex <= 5 ) {
			return instructions.get( rowIndex ).getParameters().get( columnIndex - 3 );
		}
		else {
			throw new IllegalArgumentException( "No value at " + rowIndex + "x" + columnIndex );
		}
	}

	@Override
	public String getColumnName( int column ) {
		return COLUMN_NAMES.get( column );
	}
}
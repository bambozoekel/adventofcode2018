package moerk.device;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class Device {
	private final List<Register> registers;

	private int instructionPointer;
	private Register instructionPointerRegister;

	public Device( int registers ) {
		this.registers = new ArrayList<>( registers );
		for ( int i = 0; i < registers; i++ ) {
			this.registers.add( new Register() );
		}
	}

	public int getValue( int registerIndex ) {
		return registers.get( registerIndex ).getValue();
	}

	public List<Integer> getState() {
		return registers.stream()
			.map( Register::getValue )
			.collect( Collectors.toList() );
	}

	public void setValues( List<Integer> values ) {
		for ( int i = 0; i < values.size(); i++ ) {
			registers.get( i ).setValue( values.get( i ) );
		}
	}

	public void execute( Program program ) {
		instructionPointer = 0;
		instructionPointerRegister = determineInstructionPointerRegister( program );

		System.out.println( instructionPointer + " " + getState() );
		while ( instructionPointer < program.getInstructionCount() ) {
			handleInstruction( program.getInstruction( instructionPointer ) );
		}
	}

	private Register determineInstructionPointerRegister( Program program ) {
		if ( program.getIpRegisterIndex() == Program.NO_IP_REGISTER_INDEX ) {
			return new Register();
		}
		else {
			return registers.get( program.getIpRegisterIndex() );
		}
	}

	private void handleInstruction( Instruction instruction ) {
		instructionPointerRegister.setValue( instructionPointer );
		execute( instruction );
		instructionPointer = instructionPointerRegister.getValue();
		instructionPointer++;
		System.out.println( instructionPointer + " " + getState() );
	}

	private void execute( Instruction instruction ) {
		Operation operation = instruction.getOperation();
		List<Integer> abc = instruction.getParameters();

		int newValueForC = operation.apply( this, abc.get( 0 ), abc.get( 1 ) );
		registers.get( abc.get( 2 ) ).setValue( newValueForC );
	}
}
package moerk.device;

import java.util.Collections;
import java.util.List;

/**
 * @author matthias
 */
public class Program {
	public static final int NO_IP_REGISTER_INDEX = -1;

	private final int ipRegisterIndex;
	private final List<Instruction> instructions;

	public Program( Operation operation, List<Integer> parameters ) {
		this( new Instruction( operation, parameters ) );
	}

	public Program( Instruction instruction ) {
		this( Collections.singletonList( instruction ) );
	}

	public Program( List<Instruction> instructions ) {
		this( NO_IP_REGISTER_INDEX, instructions );
	}

	public Program( int ipRegisterIndex, List<Instruction> instructions ) {
		this.ipRegisterIndex = ipRegisterIndex;
		this.instructions = instructions;
	}

	public Instruction getInstruction( int index ) {
		return instructions.get( index );
	}

	public int getInstructionCount() {
		return instructions.size();
	}

	public int getIpRegisterIndex() {
		return ipRegisterIndex;
	}
}
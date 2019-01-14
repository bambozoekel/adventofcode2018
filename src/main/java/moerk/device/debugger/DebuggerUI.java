package moerk.device.debugger;

import moerk.device.Instruction;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * @author matthias
 */
interface DebuggerUI {
	void showError( String errorMessage );
	Optional<File> selectFile();
	void showInstructions( List<Instruction> instructions );
	void markIpRegister( int ipRegisterIndex );
	void markBreakpoint( int line );
	void removeBreakpoint( int line );
	void markInstructionPointer( int instructionPointer );
	void setRegisterValue( int index, int value );
}
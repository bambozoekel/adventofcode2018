package moerk.device.debugger;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import moerk.device.Device;
import moerk.device.Instruction;
import moerk.device.Program;
import moerk.device.ProgramReader;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author matthias
 */
class Debugger {
	private DebuggerUI ui;
	private Device device;
	private Set<Integer> breakPoints;

	public Debugger( DebuggerUI ui ) {
		this.ui = ui;
		device = new Device( 6 );
		breakPoints = new HashSet<>();
	}

	void open() {
		ui.selectFile().ifPresent( this::loadProgram );
	}

	private void loadProgram( File file ) {
		try {
			tryInitializeProgram( file );
		}
		catch( IOException e ) {
			ui.showError( e.getMessage() );
		}
	}

	private void tryInitializeProgram( File file ) throws IOException {
		Program program = new ProgramReader().read( Files.readLines( file, Charsets.UTF_8 ) );
		device.load( program );
		ui.showInstructions( getInstructions( program ) );
		ui.markIpRegister( program.getIpRegisterIndex() );
		ui.markInstructionPointer( 0 );
		IntStream.range( 0, 6 ).forEach( i -> ui.setRegisterValue( i, 0 ) );
	}

	private List<Instruction> getInstructions( Program program ) {
		return IntStream.range( 0, program.getInstructionCount() )
			.mapToObj( program::getInstruction )
			.collect( Collectors.toList() );
	}

	void toggleBreakpoint( int line ) {
		if ( breakPoints.remove( line ) ) {
			ui.removeBreakpoint( line );
		}
		else {
			breakPoints.add( line );
			ui.markBreakpoint( line );
		}
	}

	void step() {
		device.step();
		updateUI();
	}

	void run() {
		while( device.isProgramRunning() && !breakPoints.contains( device.getInstructionPointer() ) ) {
			device.step();
		}

		updateUI();
	}

	private void updateUI() {
		if ( device.isProgramRunning() ) {
			ui.markInstructionPointer( device.getInstructionPointer() );
		}

		IntStream.range( 0, 6 ).forEach( i -> ui.setRegisterValue( i, device.getValue( i ) ) );
	}
}
package moerk.day19.puzzle1;

import moerk.Util;
import moerk.device.ProgramReader;
import moerk.device.Device;
import moerk.device.Program;

/**
 * @author matthias
 */
public class InstructionPointer {
	public static void main( String[] args ) {
		Program program = new ProgramReader().read( Util.lines( "day19/program.txt" ) );
		Device device = new Device( 6 );

		device.load( program );
		device.runProgram();

		System.out.println( device.getState() );
	}
}
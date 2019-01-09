package moerk.day19.puzzle1;

import moerk.day19.ProgramReader;
import moerk.device.Device;
import moerk.device.Program;

import java.util.Arrays;

/**
 * @author matthias
 */
public class InstructionPointer {
	public static void main( String[] args ) {
		Program program = new ProgramReader().read();
		Device device = new Device( 6 );
//		device.setValues( Arrays.asList( 1, 0, 0, 0, 0, 0 ) );

		device.execute( program );

		System.out.println( device.getState() );
	}
}
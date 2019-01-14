package moerk.day16.puzzle2;

import moerk.device.Device;
import moerk.device.Operation;
import moerk.day16.Sample;
import moerk.day16.SampleReader;
import moerk.device.Program;

import java.util.List;
import java.util.Map;

/**
 * @author matthias
 */
public class ExecuteProgram {
	public static void main( String[] args ) {
		List<Sample> samples = new SampleReader().read();
		Map<Integer, Operation> operations = new OperatorDetector().detect( samples );

		Program program = new ProgramReader().read( operations );

		Device device = new Device( 4 );
		device.load( program );
		device.runProgram();
		System.out.println( device.getValue( 0 ) );
	}
}
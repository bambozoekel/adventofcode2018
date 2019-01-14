package moerk.day16.puzzle1;

import moerk.day16.Sample;
import moerk.day16.SampleReader;
import moerk.device.Device;
import moerk.device.Operation;
import moerk.device.Program;
import moerk.device.operations.AddImmediate;
import moerk.device.operations.AddRegister;
import moerk.device.operations.BitWiseAndImmediate;
import moerk.device.operations.BitWiseAndRegister;
import moerk.device.operations.BitWiseOrImmediate;
import moerk.device.operations.BitWiseOrRegister;
import moerk.device.operations.EqualsImmediateRegister;
import moerk.device.operations.EqualsRegisterImmediate;
import moerk.device.operations.EqualsRegisterRegister;
import moerk.device.operations.GreaterThanImmediateRegister;
import moerk.device.operations.GreaterThanRegisterImmediate;
import moerk.device.operations.GreaterThanRegisterRegister;
import moerk.device.operations.MultiplyImmediate;
import moerk.device.operations.MultiplyRegister;
import moerk.device.operations.SetImmediate;
import moerk.device.operations.SetRegister;

import java.util.List;

/**
 * @author matthias
 */
public class MoreThanTreeOperators {
	public static void main( String[] args ) {
		List<Sample> samples = new SampleReader().read();

		List<Operation> operations = List.of(
			new AddRegister(),
			new AddImmediate(),
			new MultiplyRegister(),
			new MultiplyImmediate(),
			new BitWiseAndRegister(),
			new BitWiseAndImmediate(),
			new BitWiseOrRegister(),
			new BitWiseOrImmediate(),
			new SetRegister(),
			new SetImmediate(),
			new GreaterThanImmediateRegister(),
			new GreaterThanRegisterImmediate(),
			new GreaterThanRegisterRegister(),
			new EqualsImmediateRegister(),
			new EqualsRegisterImmediate(),
			new EqualsRegisterRegister()
		);

		Device device = new Device( 4 );

		int threeOrMore = 0;
		for ( Sample sample : samples ) {
			int match = 0;
			for ( Operation operation : operations ) {
				device.setValues( sample.getBefore() );
				device.load( new Program( operation, sample.getInstruction().subList( 1, 4 ) ) );
				device.runProgram();
				match += device.getState().equals( sample.getAfter() ) ? 1 : 0;
			}

			threeOrMore += match >= 3 ? 1 : 0;
		}

		System.out.println( threeOrMore );
	}
}
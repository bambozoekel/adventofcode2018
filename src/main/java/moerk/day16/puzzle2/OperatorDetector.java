package moerk.day16.puzzle2;

import moerk.day16.Sample;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author matthias
 */
public class OperatorDetector {
	public Map<Integer, Operation> detect( List<Sample> samples ) {
		List<Operation> operations = new ArrayList<>( List.of(
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
		) );

		Device device = new Device( 4 );

		Map<Integer, Operation> mapped = new HashMap<>();
		while ( !operations.isEmpty() ) {
			for ( Sample sample : samples ) {
				List<Operation> matches = new ArrayList<>();

				for ( Operation operation : operations ) {
					device.setValues( sample.getBefore() );
					device.load( new Program( operation, sample.getInstruction().subList( 1, 4 ) ) );
					device.runProgram();
					if ( device.getState().equals( sample.getAfter() ) ) {
						matches.add( operation );
					}
				}

				if ( matches.size() == 1 ) {
					mapped.put( sample.getInstruction().get( 0 ), matches.get( 0 ) );
					operations.remove( matches.get( 0 ) );
				}
			}
		}

		return mapped;
	}
}
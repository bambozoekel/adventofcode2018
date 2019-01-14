package moerk.device;

import com.google.common.collect.ImmutableMap;
import moerk.Util;
import moerk.device.Instruction;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class ProgramReader {
	private static final Map<String, Operation> OPERATIONS = new ImmutableMap.Builder<String, Operation>()
		.put("addr", new AddRegister())
		.put("addi", new AddImmediate())
		.put("mulr", new MultiplyRegister())
		.put("muli", new MultiplyImmediate())
		.put("banr", new BitWiseAndRegister())
		.put("bani", new BitWiseAndImmediate())
		.put("borr", new BitWiseOrRegister())
		.put("bori", new BitWiseOrImmediate())
		.put("setr", new SetRegister())
		.put("seti", new SetImmediate())
		.put("gtir", new GreaterThanImmediateRegister())
		.put("gtri", new GreaterThanRegisterImmediate())
		.put("gtrr", new GreaterThanRegisterRegister())
		.put("eqir", new EqualsImmediateRegister())
		.put("eqri", new EqualsRegisterImmediate())
		.put("eqrr", new EqualsRegisterRegister())
		.build();

	public Program read( List<String> lines ) {
		int ipRegisterIndex = Integer.parseInt( lines.get( 0 ).substring( 4 ) );

		List<Instruction> instructions = lines.stream()
			.skip( 1 )
			.map( this::parse )
			.collect( Collectors.toList() );

		return new Program( ipRegisterIndex, instructions );
	}

	private Instruction parse( String line ) {
		String[] parts = line.split( " " );
		Operation operation = OPERATIONS.get( parts[0] );
		List<Integer> parameters = Arrays.stream( parts )
			.skip( 1 )
			.map( Integer::valueOf )
			.collect( Collectors.toList() );

		return new Instruction( operation, parameters );
	}
}
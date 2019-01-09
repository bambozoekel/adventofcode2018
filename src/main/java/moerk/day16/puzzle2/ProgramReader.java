package moerk.day16.puzzle2;

import moerk.Util;
import moerk.device.Instruction;
import moerk.device.Operation;
import moerk.device.Program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class ProgramReader {
	public Program read( Map<Integer, Operation> operations ) {
		List<String> lines = Util.lines( "day16/samples.txt" );

		List<Instruction> instructions = new ArrayList<>();

		int start = getFirstProgramLine( lines );
		for ( int i = start; i < lines.size(); i++ ) {
			String[] parts = lines.get( i ).split( " " );
			Operation operation = operations.get( Integer.valueOf( parts[0] ) );
			List<Integer> parameters = Arrays.stream( parts )
				.skip( 1 )
				.map( Integer::valueOf )
				.collect( Collectors.toList() );

			instructions.add( new Instruction( operation, parameters ) );
		}

		return new Program( instructions );
	}

	private int getFirstProgramLine( List<String> lines ) {
		int i = 0;

		while ( lines.get( i ).length() > 0 || lines.get( i + 1 ).length() > 0 || lines.get( i + 2 ).length() > 0 ) {
			i++;
		}

		return i + 3;
	}
}
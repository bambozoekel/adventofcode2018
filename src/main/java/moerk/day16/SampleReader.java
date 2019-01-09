package moerk.day16;

import moerk.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class SampleReader {
	public List<Sample> read() {
		List<String> lines = Util.lines( "day16/samples.txt" );

		int sampleCount = getSampleCount( lines );
		List<Sample> samples = new ArrayList<>();
		for ( int i = 0; i < sampleCount; i++ ) {
			int l = i * 4;
			List<Integer> before = parseState( lines.get( l ) );
			List<Integer> instruction = parseInstruction( lines.get( l + 1 ) );
			List<Integer> after = parseState( lines.get( l + 2 ) );
			samples.add( new Sample( before, instruction, after ) );
		}

		return samples;
	}

	private int getSampleCount( List<String> lines ) {
		int i = 0;

		while ( lines.get( i ).length() > 0 || lines.get( i + 1 ).length() > 0 ) {
			i++;
		}

		return (i / 4) + 1;
	}

	private List<Integer> parseState( String line ) {
		String[] parts = line.substring( 9, line.length() - 1 ).split( ", " );
		return Arrays.stream( parts )
			.map( Integer::valueOf )
			.collect( Collectors.toList() );
	}

	private List<Integer> parseInstruction( String line ) {
		return Arrays.stream( line.split( " " ) )
			.map( Integer::valueOf )
			.collect( Collectors.toList() );
	}
}
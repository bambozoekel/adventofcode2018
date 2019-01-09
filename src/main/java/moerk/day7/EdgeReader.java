package moerk.day7;

import moerk.Util;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class EdgeReader {
	public Set<Edge> read() {
		return Util.lines("day7/steps.txt")
			.stream()
			.map(this::parseStep)
			.collect( Collectors.toSet() );
	}

	private Edge parseStep( String line ) {
		return new Edge( line.substring( 5, 6 ), line.substring( 36, 37 ) );
	}
}
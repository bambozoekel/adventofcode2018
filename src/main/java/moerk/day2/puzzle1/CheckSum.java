package moerk.day2.puzzle1;

import moerk.Util;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class CheckSum {
	public static void main( String[] args ) {
		int twos = 0;
		int threes = 0;

		for ( String line : Util.lines("day2/puzzle1/ids.txt")) {
			Map<Integer, Long> freqs = line.chars()
				.boxed()
				.collect( Collectors.groupingBy( Function.identity(), Collectors.counting() ) );

			twos += freqs.containsValue( 2L ) ? 1 : 0;
			threes += freqs.containsValue( 3L ) ? 1 : 0;
			System.out.println(freqs);
		}

		System.out.println(twos*threes);
	}
}
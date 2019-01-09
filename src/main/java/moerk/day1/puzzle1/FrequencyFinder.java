package moerk.day1.puzzle1;

import moerk.Util;

/**
 * @author matthias
 */
public class FrequencyFinder {
	public static void main( String[] args ) {
		int frequency = Util.lines( "day1/puzzle1/frequencies.txt" )
			.stream()
			.map( Integer::valueOf )
			.reduce( 0, Integer::sum );
		System.out.println( frequency );
	}
}
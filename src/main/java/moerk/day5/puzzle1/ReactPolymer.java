package moerk.day5.puzzle1;

import moerk.Util;
import moerk.day5.PolymerReactor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class ReactPolymer {
	public static void main( String[] args ) {
		withArray();
		withList();
	}

	private static void withArray() {
		long start = System.currentTimeMillis();
		StringBuilder polymer = new StringBuilder( Util.lines( "day5/polymer.txt" ).get(0) );
		int i = 0;
		while ( i < polymer.length() - 1 ) {
			if ( Math.abs(polymer.charAt(i)-polymer.charAt(i+1)) == 32) {
				polymer.delete( i, i+2 );
				i = Math.max(0, i-1);
			}
			else {
				i++;
			}
		}

		long stop = System.currentTimeMillis();
		System.out.println( stop - start );
		System.out.println( polymer.length() );
	}

	private static void withList() {
		long start = System.currentTimeMillis();
		List<Integer> polymer = Util.lines( "day5/polymer.txt" )
			.get(0)
			.chars()
			.boxed()
			.collect( Collectors.toCollection( ArrayList::new ) );

		int result = new PolymerReactor().react( polymer );
		long stop = System.currentTimeMillis();
		System.out.println( stop - start );
		System.out.println( result );
	}
}
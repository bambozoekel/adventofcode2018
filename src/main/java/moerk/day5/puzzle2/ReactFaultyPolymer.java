package moerk.day5.puzzle2;

import moerk.Util;
import moerk.day5.PolymerReactor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class ReactFaultyPolymer {
	public static void main( String[] args ) {
		withArray();
		withList();
	}

	private static void withArray() {
		long start = System.currentTimeMillis();
		String source = Util.lines( "day5/polymer.txt" ).get(0);

		int minimumSize = Integer.MAX_VALUE;
		for ( int l = 'A'; l <= 'Z'; l++ ) {
			StringBuilder polymer = new StringBuilder( source );

			int i = 0;
			while ( i < polymer.length() - 1 ) {
				if ( polymer.charAt( i ) == l || polymer.charAt( i ) == l + 32 ) {
					polymer.delete( i, i + 1 );
					i = Math.max(0, i-1);
				}
				else if ( Math.abs(polymer.charAt(i)-polymer.charAt(i+1)) == 32) {
					polymer.delete( i, i+2 );
					i = Math.max(0, i-1);
				}
				else {
					i++;
				}
			}

			minimumSize = Math.min( minimumSize, polymer.length() );
		}

		long stop = System.currentTimeMillis();
		System.out.println( stop - start );
		System.out.println( minimumSize );
	}

	private static void withList() {
		long start = System.currentTimeMillis();
		List<Integer> polymer = Util.lines( "day5/polymer.txt" )
			.get(0)
			.chars()
			.boxed()
			.collect( Collectors.toList());

		PolymerReactor reactor = new PolymerReactor();
		int minimumSize = Integer.MAX_VALUE;

		for ( int c = 'a'; c <= 'z'; c++ ) {
			List<Integer> cleanedPolymer = clean( polymer, c );
			int r = reactor.react( cleanedPolymer );
			minimumSize = Math.min( minimumSize, r );
		}

		long stop = System.currentTimeMillis();
		System.out.println( stop - start );
		System.out.println( minimumSize );
	}

	private static List<Integer> clean( List<Integer> polymer, int letter ) {
		return polymer.stream()
			.filter( i -> i != letter && i != letter - 32 )
			.collect( Collectors.toCollection( ArrayList::new ) );
	}
}
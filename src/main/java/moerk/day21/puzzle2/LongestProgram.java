package moerk.day21.puzzle2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author matthias
 */
public class LongestProgram {
	public static void main( String[] args ) {
		Set<Integer> beenThere = new HashSet<>();
		int r1;
		int r2;
		int r3;
		int r5 = 123;

		do {
			r5 = r5 & 456;
		}
		while ( r5 != 72 );

		r5 = 0;

		do {
			r3 = r5 | 65536;
			r5 = 733884;

			while ( true ) {
				r5 = r5 + (r3 & 255);
				r5 = r5 & 16777215;
				r5 = r5 * 65899;
				r5 = r5 & 16777215;

				if ( 256 > r3 ) {
					break;
				}
				else {
					r1 = 0;
					while ( true ) {
						r2 = r1 + 1;
						r2 = r2 * 256;

						if ( r2 > r3 ) {
							r3 = r1;
							break;
						}
						else {
							r1++;
						}
					}
				}
			}

			System.out.println( r5 );
		}
		while ( beenThere.add( r5 ) );
		System.out.println( beenThere.size() );
	}
}
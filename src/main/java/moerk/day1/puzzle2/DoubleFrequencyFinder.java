package moerk.day1.puzzle2;

import com.google.common.collect.Iterables;
import moerk.Util;

import java.util.HashSet;
import java.util.Set;

/**
 * @author matthias
 */
public class DoubleFrequencyFinder {
	public static void main( String[] args ) {
		Set<Integer> frequencies = new HashSet<>();

		int frequency = 0;
		int i=0;
		for ( String line : Iterables.cycle( Util.lines( "day1/puzzle1/frequencies.txt" ) ) ) {
			frequency += Integer.parseInt( line );
			i++;
			if (!frequencies.add(frequency)){
				System.out.println(i);
				System.out.println(frequency);
				break;
			}
		}
	}
}
package moerk.day19.puzzle2;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author matthias
 */
public class Dividers {
	public static void main( String[] args ) {
		int main = 10551396;

		Set<Integer> dividers = new TreeSet<>();

		int i = 1;
		while ( !dividers.contains( i ) ) {
			if ( main % i == 0 ) {
				dividers.add( i );
				dividers.add( main / i );
			}

			i++;
		}

		System.out.println( dividers );
		System.out.println(dividers.stream().reduce( 0, Integer::sum));
	}
}
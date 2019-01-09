package moerk.day12.puzzle1;

import moerk.day12.Grower;
import moerk.day12.Situation;
import moerk.day12.SituationReader;

/**
 * @author matthias
 */
public class GrowPlants {
	public static void main( String[] args ) {
		Situation situation = new SituationReader().read();
		Grower grower = new Grower( situation );
		grower.grow( 20 );

		System.out.println( grower.getSum() );
	}
}
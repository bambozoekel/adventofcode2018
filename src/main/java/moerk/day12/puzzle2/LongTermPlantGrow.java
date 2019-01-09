package moerk.day12.puzzle2;

import moerk.day12.Grower;
import moerk.day12.Situation;
import moerk.day12.SituationReader;

/**
 * @author matthias
 */
public class LongTermPlantGrow {
	public static void main( String[] args ) {
		Situation situation = new SituationReader().read();
		Grower grower = new Grower( situation );
		grower.grow( 112 );

		System.out.println( grower.getSum( 50000000000L - 112L ) );
	}
}
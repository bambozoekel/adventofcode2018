package moerk.day15.puzzle1;

import moerk.day15.BattleSimulator;
import moerk.day15.EntityFactory;
import moerk.day15.Grotto;
import moerk.day15.GrottoReader;
import moerk.day15.Warrior;

/**
 * @author matthias
 */
public class Battle {
	public static void main( String[] args ) {
		Grotto grotto = new GrottoReader().read( new EntityFactory() );

		int rounds = new BattleSimulator().simulate( grotto );

		int hitPointsLeft = grotto.getWarriors()
			.stream()
			.map( Warrior::getHitPoints )
			.reduce( 0, Integer::sum );

		grotto.print();
		System.out.println("----------------");
		for ( Warrior w : grotto.getWarriors()) {
			System.out.println( w.toString() + ":" + w.getHitPoints() );
		}
		System.out.println( "Hitpoints left: " + hitPointsLeft );
		System.out.println( "Rounds: " + rounds );
		System.out.println( rounds * hitPointsLeft );
	}
}
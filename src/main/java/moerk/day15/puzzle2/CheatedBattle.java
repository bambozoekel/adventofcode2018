package moerk.day15.puzzle2;

import moerk.day15.BattleSimulator;
import moerk.day15.Elve;
import moerk.day15.EntityFactory;
import moerk.day15.Grotto;
import moerk.day15.GrottoReader;
import moerk.day15.Warrior;

/**
 * @author matthias
 */
public class CheatedBattle {
	public static void main( String[] args ) {
		GrottoReader reader = new GrottoReader();
		BattleSimulator simulator = new BattleSimulator();
		int elveAttack = 4;
		Grotto grotto;
		int rounds;

		do {
			grotto = reader.read( new EntityFactory( elveAttack ) );
			rounds = simulator.simulate( grotto );
			elveAttack++;
		}
		while ( grotto.getWarriors().stream().filter( w -> w instanceof Elve ).count() < 10 );

		int hitpointsLeft = grotto.getWarriors()
			.stream()
			.map( Warrior::getHitPoints )
			.reduce( 0, Integer::sum );

		System.out.println( "Optimal elve attack: " + elveAttack-- );
		System.out.println( "hitpoints left: " + hitpointsLeft );
		System.out.println( "Rounds: " + rounds );
		System.out.println( hitpointsLeft * rounds );
	}
}
package moerk.day15.puzzle1;

import moerk.day15.Grotto;
import moerk.day15.GrottoReader;
import moerk.day15.Warrior;

import java.awt.Point;
import java.util.Comparator;
import java.util.Set;

/**
 * @author matthias
 */
public class Battle {
	public static void main( String[] args ) {
		Grotto grotto = new GrottoReader().read();

		int rounds = 0;
		boolean fullRound = true;
		while ( fullRound ) {
			long start = System.currentTimeMillis();

			fullRound = grotto.getWarriors()
				.stream()
				.sorted( getComparator( grotto ) )
				.map( w -> act( grotto, w ) )
				.reduce( true, Boolean::logicalAnd );

			grotto.print();

			if ( fullRound ) {
				rounds++;
			}

			for ( Warrior w : grotto.getWarriors()) {
				System.out.println( w.toString() + ":" + w.getHitPoints() );
			}
			long stop = System.currentTimeMillis();
			System.out.println(rounds + " " + grotto.getWarriors().size() + " " + (stop -start) );
			System.out.println("----------------------------------");
		}

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

	private static Comparator<Warrior> getComparator( Grotto grotto ) {
		return Comparator.comparing( grotto::getY )
			.thenComparing( Comparator.comparing( grotto::getX ) );
	}

	private static boolean act( Grotto grotto, Warrior warrior ) {
		if ( grotto.getWarriors().stream().noneMatch( warrior::isEnemy )) {
			return false;
		}

		if ( warrior.isDead() ) {
			return true;
		}

		if ( grotto.getAttackableOpponents( warrior ).isEmpty() ) {
			grotto.getNextLocationToClosestOpponent( warrior )
				.stream()
				.min( Comparator.comparing( Point::getY ).thenComparing( Comparator.comparing( Point::getX ) ) )
				.ifPresent( p -> grotto.move( warrior, p ) );
		}
		Set<Warrior> opponents = grotto.getAttackableOpponents( warrior );
		opponents.stream()
			.min( Comparator.comparing( Warrior::getHitPoints ).thenComparing( getComparator( grotto ) ) )
			.ifPresent( w -> {
				System.out.println( warrior.getClass().getSimpleName() + " (" + warrior.getHitPoints() + ") at " + grotto.getX(warrior) + "x" + grotto.getY(warrior) + " hits " + w.getClass().getSimpleName() + "(" + w.getHitPoints() + ") at " + grotto.getX(w) + "x" + grotto.getY(w));
				w.hit();
			} );
		grotto.cleanUp();

		return true;
	}
}
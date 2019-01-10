package moerk.day15;

import java.awt.Point;
import java.util.Comparator;
import java.util.Set;

/**
 * @author matthias
 */
public class BattleSimulator {
	public int simulate( Grotto grotto ) {
		int rounds = 0;
		boolean fullRound = true;
		while ( fullRound ) {
			fullRound = grotto.getWarriors()
				.stream()
				.sorted( getComparator( grotto ) )
				.map( w -> act( grotto, w ) )
				.reduce( true, Boolean::logicalAnd );

			grotto.print();

			if ( fullRound ) {
				rounds++;
			}
		}

		return rounds;
	}

	private Comparator<Warrior> getComparator( Grotto grotto ) {
		return Comparator.comparing( grotto::getY )
			.thenComparing( Comparator.comparing( grotto::getX ) );
	}

	private boolean act( Grotto grotto, Warrior warrior ) {
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
			.ifPresent( enemy -> {
				System.out.println( warrior.getClass().getSimpleName() + " (" + warrior.getHitPoints() + ") at " + grotto.getX(warrior) + "x" + grotto.getY(warrior) + " hits " + enemy.getClass().getSimpleName() + "(" + enemy.getHitPoints() + ") at " + grotto.getX(enemy) + "x" + grotto.getY(enemy));
				warrior.attack(enemy);
			} );
		grotto.cleanUp();

		return true;
	}
}
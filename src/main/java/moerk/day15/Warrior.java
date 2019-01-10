package moerk.day15;

/**
 * @author matthias
 */
public abstract class Warrior implements Entity {
	private int hitPoints;
	private final int attack;

	Warrior( int attack ) {
		hitPoints = 200;
		this.attack = attack;
	}

	@Override
	public boolean passable() {
		return false;
	}

	public void attack( Warrior enemy ) {
		enemy.hitPoints -= attack;
	}

	public boolean isDead() {
		return hitPoints <= 0;
	}

	public int getHitPoints() {
		return hitPoints;
	}
}
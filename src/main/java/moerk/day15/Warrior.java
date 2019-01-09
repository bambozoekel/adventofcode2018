package moerk.day15;

/**
 * @author matthias
 */
public abstract class Warrior implements Entity {
	private int hitPoints;

	protected Warrior() {
		hitPoints = 200;
	}

	@Override
	public boolean passable() {
		return false;
	}

	@Override
	public void hit() {
		hitPoints -= 3;
	}

	public boolean isDead() {
		return hitPoints <= 0;
	}

	public int getHitPoints() {
		return hitPoints;
	}
}
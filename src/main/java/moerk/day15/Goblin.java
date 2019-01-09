package moerk.day15;

/**
 * @author matthias
 */
public class Goblin extends Warrior {
	@Override
	public boolean isEnemy( Entity entity ) {
		return entity instanceof Elve;
	}

	@Override
	public String toString() {
		return "G";
	}
}
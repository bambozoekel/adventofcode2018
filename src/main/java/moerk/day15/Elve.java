package moerk.day15;

/**
 * @author matthias
 */
public class Elve extends Warrior {
	@Override
	public boolean isEnemy( Entity entity ) {
		return entity instanceof Goblin;
	}

	@Override
	public String toString() {
		return "E";
	}
}
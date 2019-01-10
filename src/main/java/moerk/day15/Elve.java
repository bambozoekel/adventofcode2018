package moerk.day15;

/**
 * @author matthias
 */
public class Elve extends Warrior {
	public Elve() {
		super( 3 );
	}

	public Elve( int attack ) {
		super( attack );
	}

	@Override
	public boolean isEnemy( Entity entity ) {
		return entity instanceof Goblin;
	}

	@Override
	public String toString() {
		return "E";
	}
}
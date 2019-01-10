package moerk.day15;

/**
 * @author matthias
 */
public enum EmptySpace implements Entity {
	INSTANCE;

	@Override
	public boolean passable() {
		return true;
	}

	@Override
	public boolean isEnemy( Entity entity ) {
		return false;
	}

	@Override
	public String toString() {
		return ".";
	}
}
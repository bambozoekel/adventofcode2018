package moerk.day15;

/**
 * @author matthias
 */
public enum Wall implements Entity {
	INSTANCE;

	@Override
	public boolean passable() {
		return false;
	}

	@Override
	public boolean isEnemy( Entity entity ) {
		return false;
	}

	@Override
	public String toString() {
		return "#";
	}}
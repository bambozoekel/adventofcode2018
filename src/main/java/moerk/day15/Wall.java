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
	public void hit() {
		throw new RuntimeException( "Don't hit the wall!" );
	}


	@Override
	public String toString() {
		return "#";
	}}
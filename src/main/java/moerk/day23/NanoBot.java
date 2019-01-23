package moerk.day23;

/**
 * @author matthias
 */
public class NanoBot {
	private final Point location;
	private final int radius;

	public NanoBot( Point location, int radius ) {
		this.location = location;
		this.radius = radius;
	}

	public Point getLocation() {
		return location;
	}

	public int getRadius() {
		return radius;
	}

	public int getDistance( NanoBot other ) {
		return location.getDistance( other.location );
	}

	public boolean intersects( Cube cube ) {
		return cube.getDistance( location ) <= radius;
	}

	public boolean isInRange( Point point ) {
		return location.getDistance( point ) <= radius;
	}

	@Override
	public String toString() {
		return location + ", " + radius;
	}
}
package moerk.day23;

import com.google.common.base.Objects;

/**
 * @author matthias
 */
public class Point {
	private final int x;
	private final int y;
	private final int z;

	public Point( int x, int y, int z ) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int getDistance( Point other ) {
		return Math.abs( x - other.x ) + Math.abs( y - other.y ) + Math.abs( z - other.z );
	}

	@Override
	public boolean equals( Object o ) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		Point point = (Point)o;
		return x == point.x &&
			y == point.y &&
			z == point.z;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode( x, y, z );
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}
}
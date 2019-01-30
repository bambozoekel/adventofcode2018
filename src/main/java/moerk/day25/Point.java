package moerk.day25;

/**
 * @author matthias
 */
public class Point {
	private final int x;
	private final int y;
	private final int z;
	private final int t;

	public Point( int x, int y, int z, int t ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.t = t;
	}

	public int getDistance( Point point ) {
		return Math.abs( x - point.x ) + Math.abs( y - point.y ) + Math.abs( z - point.z ) + Math.abs( t - point.t );
	}
}
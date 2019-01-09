package moerk.day6;

import java.awt.Point;

/**
 * @author matthias
 */
public class PointUtil {
	private PointUtil() {}

	public static int distance( Point p, int x, int y ) {
		return Math.abs( p.x - x ) + Math.abs( p.y - y );
	}
}
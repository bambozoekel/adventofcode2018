package moerk.day6;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Set;

/**
 * @author matthias
 */
public class BoundCalculator {
	public Rectangle calculateBounds( Set<Point> points ) {
		int minx = Integer.MAX_VALUE;
		int miny = Integer.MAX_VALUE;
		int maxx = 0;
		int maxy = 0;
		for ( Point point : points) {
			minx = Math.min(minx, point.x);
			miny = Math.min(miny, point.y);
			maxx = Math.max(maxx, point.x);
			maxy = Math.max(maxy, point.y);
		}

		return new Rectangle( minx - 50, miny - 50, maxx - minx + 100, maxy - miny + 100 );
	}
}
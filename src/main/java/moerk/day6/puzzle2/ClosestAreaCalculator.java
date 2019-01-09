package moerk.day6.puzzle2;

import moerk.day6.BoundCalculator;
import moerk.day6.PointReader;
import moerk.day6.PointUtil;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Set;

/**
 * @author matthias
 */
public class ClosestAreaCalculator {
	public static void main( String[] args ) {
		long start = System.currentTimeMillis();
		Set<Point> points = new PointReader().read();

		Rectangle bounds = new BoundCalculator().calculateBounds( points );

		int coordinateLessThan10K = 0;
		for ( int x = bounds.x; x < bounds.x + bounds.width; x++ ) {
			for ( int y = bounds.y; y < bounds.y + bounds.height; y++ ) {
				int totalDistance = totalDistance( points, x, y );
				if ( totalDistance < 10000 ) {
					coordinateLessThan10K++;
				}
			}
		}

		System.out.println( coordinateLessThan10K );
		long stop = System.currentTimeMillis();
		System.out.println( stop - start );
	}

	private static int totalDistance( Set<Point> points, int x, int y ) {
		return points.stream()
			.map( p -> PointUtil.distance( p, x, y ) )
			.reduce( 0, Integer::sum );
	}
}
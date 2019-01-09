package moerk.day6.puzzle1;

import moerk.day6.BoundCalculator;
import moerk.day6.PointReader;
import moerk.day6.PointUtil;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class AreaCalculator {
	public static void main( String[] args ) {
		long start = System.currentTimeMillis();
		Set<Point> points = new PointReader().read();

		Rectangle bounds = new BoundCalculator().calculateBounds( points );
		Set<Point> infinite = whoIsInfinite( points, bounds );
		Map<Point, Integer> bounded = new HashMap<>();

		for ( int x = bounds.x + 1; x < bounds.x + bounds.width - 2; x++ ) {
			for ( int y = bounds.y + 1; y < bounds.y + bounds.height - 2; y++ ) {
				owner( points, x, y )
					.filter( p -> !infinite.contains( p ) )
					.ifPresent( p -> bounded.merge( p, 1, Integer::sum ) );
			}
		}

		bounded.values()
			.stream()
			.max( Comparator.naturalOrder() )
			.ifPresent( System.out::println );

		long stop = System.currentTimeMillis();
		System.out.println( stop - start );
	}

	private static Set<Point> whoIsInfinite( Set<Point> owners, Rectangle bounds ) {
		Set<Point> infinite = new HashSet<>();

		for ( int x = bounds.x; x < bounds.x + bounds.width; x++ ) {
			owner( owners, x, bounds.y ).ifPresent( infinite::add );
			owner( owners, x, bounds.y + bounds.height - 1 ).ifPresent( infinite::add );
		}

		for ( int y = bounds.y + 1; y < bounds.y + bounds.height - 2; y++ ) {
			owner( owners, bounds.x, y ).ifPresent( infinite::add );
			owner( owners, bounds.x + bounds.width - 1, y ).ifPresent( infinite::add );
		}

		return infinite;
	}

	private static Optional<Point> owner( Set<Point> owners, int x, int y ) {
		return owners.stream()
			.collect( Collectors.groupingBy( owner -> PointUtil.distance( owner, x, y ) ) )
			.entrySet()
			.stream()
			.min( Comparator.comparing( Map.Entry::getKey ) )
			.filter( e -> e.getValue().size() == 1 )
			.map( e -> e.getValue().get( 0 ) );
	}
}
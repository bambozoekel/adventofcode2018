package moerk.day25.puzzle1;

import moerk.day25.Point;
import moerk.day25.PointReader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class Constellations {
	public static void main( String[] args ) {
		int constellations = 0;

		var points = new ArrayList<>( new PointReader().read() );
		while ( !points.isEmpty() ) {
			var queue = new LinkedList<Point>();
			queue.offer( points.get( 0 ) );
			points.remove( 0 );

			while ( !queue.isEmpty() ) {
				Point p = queue.poll();
				Set<Point> neighbours = getNeighbours( p, points );
				neighbours.forEach( queue::offer );
				points.removeAll( neighbours );
			}

			constellations++;
		}

		System.out.println( constellations );
	}

	private static Set<Point> getNeighbours( Point point, List<Point> points ) {
		return points.stream()
			.filter( p -> p.getDistance( point ) <= 3 )
			.collect( Collectors.toSet() );
	}
}
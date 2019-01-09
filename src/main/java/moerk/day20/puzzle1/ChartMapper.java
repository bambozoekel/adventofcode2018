package moerk.day20.puzzle1;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Range;
import moerk.PointCache;
import moerk.Util;
import moerk.day20.Chart;
import moerk.day20.Direction;

import java.awt.Point;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * @author matthias
 */
public class ChartMapper {
	private static final CharMatcher NONDIRECTING = CharMatcher.anyOf( "NEWS$" );

	public static void main( String[] args ) {
		String line = Util.lines( "day20/regex.txt" ).get(0);

		Chart chart = new Chart();
		long start = System.currentTimeMillis();
		walk( line, chart );

		PointCache.log();
		chart.print();

		findShortesRouteToFarthesRoom( chart );
		long stop = System.currentTimeMillis();
		System.out.println( stop - start );
	}

	private static void walk( String line, Chart chart ) {
		var availableRanges = getRanges( line );
		Deque<Range<Integer>> ranges = new LinkedList<>();
		Deque<Point> positions = new LinkedList<>();
		Deque<Integer> indices = new LinkedList<>();
		int index = 1;
		Point position = PointCache.get( 0, 0 );

		while ( true ) {
			char c = line.charAt( index );
			if ( c == '(' ) {
				Range<Integer> range = availableRanges.get( index );
				positions.push( position );
				ranges.push( range );
			}
			else if ( c == ')' ) {
				ranges.pop();
				positions.pop();
			}
			else if ( c == '|' ) {
				indices.push( index );
				for ( Range<Integer> range : ranges ) {
					if ( NONDIRECTING.matches( line.charAt( range.upperEndpoint() + 1 ) ) ) {
						index = range.upperEndpoint();
					}
				}
			}
			else if ( c == '$' ) {
				if ( ranges.isEmpty() ) {
					break;
				}

				index = indices.pop();
				position = positions.peek();
			}
			else {
				Direction d = Direction.of( c );
				Point next = d.move( position );
				chart.connectRooms( position, next );
				position = next;
			}

			index++;
		}
	}

	private static Map<Integer, Range<Integer>> getRanges( String line) {
		var ranges = new HashMap<Integer, Range<Integer>>();

		Deque<Integer> openingLocations = new LinkedList<>();

		for ( int i = 0; i < line.length(); i++ ) {
			if ( line.charAt( i ) == '(' ) {
				openingLocations.push( i );
			}
			else if ( line.charAt( i ) == ')' ) {
				int start = openingLocations.pop();
				ranges.put( start, Range.closedOpen( start, i ) );
			}
		}

		return ranges;
	}

	private static void findShortesRouteToFarthesRoom( Chart chart ) {
		Set<Point> current;
		Set<Point> next = new HashSet<>();
		next.add( PointCache.get( 0, 0 ) );
		Set<Point> beenThere = new HashSet<>();
		beenThere.add( PointCache.get( 0, 0 ) );
		int doors = 0;
		int moreThan1000 = 0;

		do {
			current = next;
			next = new HashSet<>();

			current.stream()
				.flatMap( p -> chart.get( p ).getNeighbours().stream() )
				.filter( beenThere::add )
				.forEach( next::add );

			if ( !next.isEmpty() ) {
				doors++;
			}

			if ( doors >= 1000 ) {
				moreThan1000 += next.size();
			}
		}
		while ( !next.isEmpty() );

		System.out.println( "#doors on shortest route to farthest door: " +  doors );
		System.out.println( "More than 1000: " + moreThan1000 );
	}
}
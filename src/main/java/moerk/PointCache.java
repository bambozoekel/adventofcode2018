package moerk;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.awt.Point;

/**
 * @author matthias
 */
public class PointCache {
	private static final Table<Integer, Integer, Point> CACHE = HashBasedTable.create();

	private static long creationCount;

	public static Point get( int x, int y ) {


		Point point = CACHE.get( x, y );
		if ( point == null ) {
			creationCount++;
			point = new Point( x, y );
			CACHE.put( x, y, point );
		}

		return point;
	}

	public static void log() {
		System.out.println( "Created " + creationCount + " points" );
	}
}
package moerk.day20;

import moerk.PointCache;

import java.awt.Point;

/**
 * @author matthias
 */
public enum Direction {
	NORTH( 0, -1 ),
	SOUTH( 0, 1 ),
	EAST( 1, 0 ),
	WEST( -1, 0 );

	private int dx;
	private int dy;

	Direction( int dx, int dy ) {
		this.dx = dx;
		this.dy = dy;
	}

	public static Direction of( char c )  {
		if ( c == 'N' ) {
			return NORTH;
		}
		else if ( c == 'W' ) {
			return WEST;
		}
		else if ( c == 'S' ) {
			return SOUTH;
		}
		else if ( c == 'E' ) {
			return EAST;
		}
		else {
			throw new IllegalArgumentException( "" + c );
		}
	}

	public Point move( Point p ) {
		return PointCache.get( p.x + dx, p.y + dy );
	}
}
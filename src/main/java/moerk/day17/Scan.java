package moerk.day17;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author matthias
 */
public class Scan {
	private final Table<Integer, Integer, Type> map;
	private int minX = Integer.MAX_VALUE;
	private int maxX;
	private int minY = Integer.MAX_VALUE;
	private int maxY;

	public Scan() {
		map = HashBasedTable.create();
		set( 500, 0, Type.SOURCE );
	}

	public void set( int x, int y, Type type ) {
		map.put( x, y, type );

		if ( type == Type.CLAY ) {
			minX = Math.min( minX, x );
			maxX = Math.max( maxX, x );
			minY = Math.min( minY, y );
			maxY = Math.max( maxY, y );
		}
	}

	public Type get( int x, int y ) {
		Type type = map.get( x, y );
		if ( type == null ) {
			return Type.SAND;
		}
		else {
			return type;
		}
	}

	public int getMinX() {
		return minX;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getWaterPositionCount() {
		return getTypeCount( EnumSet.of( Type.WATER, Type.FLOW ) );
	}

	public int getWaterCount() {
		return getTypeCount( EnumSet.of( Type.WATER ) );
	}

	private int getTypeCount( Set<Type> types ) {
		int count = 0;
		for ( int x = getMinX() - 1; x <= getMaxX() + 1; x++ ) {
			for ( int y = getMinY(); y <= getMaxY(); y++ ) {
				if ( types.contains( get( x, y ) ) ) {
					count++;
				}
			}
		}

		return count;
	}
}
package moerk.day18;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class Area {
	private Table<Integer, Integer, Type> map;
	private Table<Integer, Integer, Type> tempMap;
	private final Map<Point, Set<Point>> neighbours;
	private final int width;
	private final int height;

	public Area( int width, int height ) {
		this.width = width;
		this.height = height;
		tempMap = HashBasedTable.create();
		neighbours = new HashMap<>();
		buildNeighbours();
	}

	private void buildNeighbours() {
		for ( int x = 0; x < width; x++ ) {
			for ( int y = 0; y < height; y++ ) {
				setNeighbourds( x, y );
			}
		}
	}

	private void setNeighbourds( int x, int y ) {
		Set<Point> points = new HashSet<>();
		for ( int dx = Math.max( 0, x - 1 ); dx <= Math.min( width - 1, x + 1 ); dx++ ) {
			for ( int dy = Math.max( 0, y - 1 ); dy <= Math.min( height - 1, y + 1 ); dy++ ) {
				if ( dx != x || dy != y ) {
					points.add( new Point( dx, dy ) );
				}
			}
		}

		neighbours.put( new Point( x, y ), points );
	}

	public void set( int x, int y, Type type ) {
		tempMap.put( x, y, type );
	}

	public void commit() {
		map = ArrayTable.create( tempMap );
		tempMap.clear();
	}

	public Type get( int x, int y ) {
		return map.get( x, y );
	}

	public long getTreeCount() {
		return getTypeCount( Type.TREES );
	}

	public long getLumberYardCount() {
		return getTypeCount( Type.LUMBERYARD );
	}

	private long getTypeCount( Type type ) {
		return map.values()
			.stream()
			.filter( t -> t == type )
			.count();
	}

	public Map<Type, Integer> getNeighbouringTypeCount( int x, int y ) {
		Map<Type, Integer> counts = new HashMap<>();
		counts.put( Type.OPEN, 0 );
		counts.put( Type.TREES, 0 );
		counts.put( Type.LUMBERYARD, 0 );

		neighbours.get( new Point( x, y ) )
			.stream()
			.map( p -> get( p.x, p.y ) )
			.forEach( t -> counts.merge( t, 1, Integer::sum ) );

		return counts;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void print() {
		for ( int y = 0; y < height; y++ ) {
			for ( int x = 0; x < width; x++ ) {
				String c;
				Type type = get( x, y );
				if ( type == Type.TREES ) {
					c = "|";
				}
				else if ( type == Type.LUMBERYARD ) {
					c = "#";
				}
				else {
					c = ".";
				}

				System.out.print( c );
			}
			System.out.println();
		}
	}
}
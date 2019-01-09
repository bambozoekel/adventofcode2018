package moerk.day20;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import moerk.PointCache;

import java.awt.Point;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author matthias
 */
public class Chart {
	private final Table<Integer, Integer, Room> map;

	public Chart() {
		map = HashBasedTable.create();
	}

	public Room get( Point p ) {
		Room room = map.get( p.x, p.y );
		if ( room == null ) {
			room = new Room();
			map.put( p.x, p.y, room );
		}

		return room;
	}

	public void connectRooms( Point p1, Point p2 ) {
		get( p1 ).addNeighbour( p2 );
		get( p2 ).addNeighbour( p1 );
	}

	public void print() {
		TreeSet<Integer> xs = new TreeSet<>( map.rowKeySet() );
		TreeSet<Integer> ys = new TreeSet<>( map.columnKeySet() );

		for ( int i = 0; i < xs.size() * 2 + 1; i++ ) {
			System.out.print( "#" );
		}
		System.out.println();

		for ( int y = ys.first(); y <= ys.last(); y++ ) {
			System.out.print( "#" );
			for ( int x = xs.first(); x <= xs.last(); x++ ) {
				Room room = map.get( x, y );
				if ( room == null ) {
					System.out.print( "##" );
				}
				else {
					if ( x == 0 && y == 0 ) {
						System.out.print( "X" );
					}
					else {
						System.out.print( "." );
					}

					if ( room.hasNeighbour( PointCache.get( x + 1, y ) ) ) {
						System.out.print( "|" );
					}
					else {
						System.out.print( "#" );
					}
				}
			}

			System.out.println();

			System.out.print( "#" );
			for ( int x = xs.first(); x <= xs.last(); x++ ) {
				Room room = map.get( x, y );
				if ( room == null ) {
					System.out.print( "##" );
				}
				else if ( room.hasNeighbour( PointCache.get( x, y + 1 ) ) ) {
					System.out.print( "-#" );
				}
				else {
					System.out.print( "##" );
				}
			}

			System.out.println();
		}
	}
}
package moerk.day22;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 * @author matthias
 */
public class Cave {
	private final Table<Integer, Integer, Region> map;
	private final Point target;
	private final int depth;
	private final int expansion;

	public Cave( Scan scan ) {
		this( scan, 0 );
	}

	public Cave( Scan scan, int expansion ) {
		map = HashBasedTable.create();
		this.target = scan.getTarget();
		this.depth = scan.getDepth();
		this.expansion = expansion;
		scan();
	}

	public Cave( Table<Integer, Integer, Region> preScanned, Point target ) {
		map = HashBasedTable.create( preScanned );
		this.target = target;
		depth = 0;
		expansion = 4;
	}

	private void scan() {
		for ( int y = 0; y <= getHeight(); y++ ) {
			for ( int x = 0; x <= getWidth(); x++ ) {
				map.put( x, y, determineRegion( x, y ) );
			}
		}
	}

	public int getWidth() {
		return target.x + expansion;
	}

	public int getHeight() {
		return target.y + expansion;
	}

	private Region determineRegion( int x, int y ) {
		return new Region( determineErosionLevel( x, y ), x, y );
	}

	private long determineErosionLevel( int x, int y ) {
		return (determinGeologicIndex( x, y ) + depth) % 20183;
	}

	private long determinGeologicIndex( int x, int y ) {
		if ( x == 0 && y == 0 || x == target.x && y == target.y ) {
			return 0;
		}
		else if ( y == 0 ) {
			return x * 16807L;
		}
		else if ( x == 0 ) {
			return y * 48271L;
		}
		else {
			return map.get( x - 1, y ).getErosionLevel() * map.get( x, y - 1 ).getErosionLevel();
		}
	}

	public Region get( int x, int y ) {
		return map.get( x, y );
	}

	public Map<Node, Integer> getNeighbours( Node node ) {
		Map<Node, Integer> edges = new HashMap<>();
		edges.put( node.getOther(), 7 );

		Region region = node.getRegion();

		for ( int dx = Math.max( 0, region.getX() - 1 ); dx <= Math.min( getWidth() - 1, region.getX() + 1 ); dx++ ) {
			for ( int dy = Math.max( 0, region.getY() - 1 ); dy <= Math.min( getHeight() - 1, region.getY() + 1 ); dy++ ) {
				if ( Math.abs( region.getX() - dx ) + Math.abs( region.getY() - dy ) == 1 ) {
					Region neighbour = get( dx, dy );
					Node neighbourNode = neighbour.getNode( node.getGear() );
					if ( neighbourNode != null ) {
						edges.put( neighbourNode, 1 );
					}
				}
			}
		}

		return edges;
	}

	public Point getTarget() {
		return target;
	}
}
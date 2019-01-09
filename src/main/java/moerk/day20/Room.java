package moerk.day20;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * @author matthias
 */
public class Room {
	private final Set<Point> neighbours;

	public Room() {
		neighbours = new HashSet<>();
	}

	public void addNeighbour( Point neighbour ) {
		neighbours.add( neighbour );
	}

	public boolean hasNeighbour( Point p ) {
		return neighbours.contains( p );
	}

	public Set<Point> getNeighbours() {
		return neighbours;
	}
}
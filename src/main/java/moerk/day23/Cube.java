package moerk.day23;

import java.util.HashSet;
import java.util.Set;

/**
 * @author matthias
 */
public class Cube {
	private final Point origin;
	private final int size;

	public Cube( Point origin, int size ) {
		this.origin = origin;
		this.size = size;
	}

	public Point getOrigin() {
		return origin;
	}

	public int getSize() {
		return size;
	}

	public Set<Cube> subCubify() {
		Set<Cube> subCubes = new HashSet<>();
		int offset = size == 1 ? 1 : size / 2;
		int newSize = size == 1 ? 0 : size / 2;

		subCubes.add( new Cube( origin, newSize ) );
		subCubes.add( new Cube( new Point( origin.getX() + offset, origin.getY(), origin.getZ() ), newSize ) );
		subCubes.add( new Cube( new Point( origin.getX(), origin.getY() + offset, origin.getZ() ), newSize ) );
		subCubes.add( new Cube( new Point( origin.getX(), origin.getY(), origin.getZ() + offset ), newSize ) );
		subCubes.add( new Cube( new Point( origin.getX() + offset, origin.getY() + offset, origin.getZ() ), newSize ) );
		subCubes.add( new Cube( new Point( origin.getX() + offset, origin.getY(), origin.getZ() + offset ), newSize ) );
		subCubes.add( new Cube( new Point( origin.getX(), origin.getY() + offset, origin.getZ() + offset ), newSize ) );
		subCubes.add( new Cube( new Point( origin.getX() + offset, origin.getY() + offset, origin.getZ() + offset ), newSize ) );

		return subCubes;
	}

	public int getDistance( Point point ) {
		Point origin2 = new Point( origin.getX() + size, origin.getY() + size, origin.getZ() + size );

		return (point.getDistance(origin) + point.getDistance(origin2) - origin.getDistance( origin2 )) / 2;
	}
	
	@Override
	public String toString() {
		return origin + " " + size;
	}
}
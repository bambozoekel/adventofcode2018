package moerk.day22;

import java.awt.Point;

/**
 * @author matthias
 */
public class Scan {
	private final int depth;
	private final Point target;

	public Scan( int depth, Point target ) {
		this.depth = depth;
		this.target = target;
	}

	public int getDepth() {
		return depth;
	}

	public Point getTarget() {
		return target;
	}
}
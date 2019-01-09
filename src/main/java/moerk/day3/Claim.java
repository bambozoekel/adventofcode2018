package moerk.day3;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

/**
 * @author matthias
 */
public class Claim {
	private String id;
	private Rectangle rectangle;

	public Claim( String id, Rectangle rectangle ) {
		this.id = id;
		this.rectangle = rectangle;
	}

	public Set<Point> getOverlappingPoints(Claim c) {
		Set<Point> overlapping = new HashSet<>();
		Rectangle intersecting = rectangle.intersection( c.rectangle );
		for ( int x = intersecting.x; x < intersecting.x + intersecting.width; x++ ) {
			for ( int y = intersecting.y; y < intersecting.y + intersecting.height; y++ ) {
				overlapping.add(new Point(x,y));
			}
		}

		return overlapping;
	}

	public String getId() {
		return id;
	}

	public boolean overlaps(Claim c) {
		Rectangle intersecting = rectangle.intersection( c.rectangle );
		return intersecting.width > 0 && intersecting.height > 0;
	}
}
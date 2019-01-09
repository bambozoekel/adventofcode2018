package moerk.day6;

import moerk.Util;

import java.awt.Point;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class PointReader {
	public Set<Point> read() {
		return Util.lines("day6/positions.txt")
			.stream()
			.map(this::parsePoint)
			.collect( Collectors.toSet());
	}

	private Point parsePoint(String line) {
		String[] parts = line.split(",");
		int x = Integer.parseInt( parts[0].trim() );
		int y = Integer.parseInt( parts[1].trim() );
		return new Point(x,y);
	}
}
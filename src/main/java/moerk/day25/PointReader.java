package moerk.day25;

import moerk.Util;

import java.util.List;

/**
 * @author matthias
 */
public class PointReader {
	public List<Point> read() {
		return Util.read( "day25/points.txt", this::parse );
	}

	private Point parse( String line ) {
		String[] values = line.split( "," );
		return new Point( Integer.parseInt( values[0] ), Integer.parseInt( values[1] ), Integer.parseInt( values[2] ), Integer.parseInt( values[3] ) );
	}
}
package moerk.day10;

import moerk.Util;

import java.util.List;

/**
 * @author matthias
 */
public class StarReader {
	public List<Star> read() {
		return Util.read("day10/test.txt",this::parseStar);
	}

	private Star parseStar( String line ) {
		int startx = Integer.parseInt( line.substring( 10, 16 ).trim() );
		int starty = Integer.parseInt( line.substring( 18, 24 ).trim() );
		int dvx = Integer.parseInt( line.substring( 36, 38 ).trim() );
		int dvy = Integer.parseInt( line.substring( 40, 42 ).trim() );

		return new Star( startx, starty, dvx, dvy );
	}
}
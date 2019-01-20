package moerk.day22;

import moerk.Util;

import java.awt.Point;
import java.util.List;

/**
 * @author matthias
 */
public class ScanReader {
	public Scan read() {
		List<String> lines = Util.lines( "day22/cave.txt" );
		int depth = Integer.parseInt( lines.get( 0 ).substring( 7 ) );
		int targetX = Integer.parseInt( lines.get( 1 ).substring( 8, lines.get( 1 ).indexOf( ',' ) ) );
		int targetY = Integer.parseInt( lines.get( 1 ).substring( lines.get( 1 ).indexOf( ',' ) + 1 ) );

		return new Scan( depth, new Point( targetX, targetY ) );
	}
}
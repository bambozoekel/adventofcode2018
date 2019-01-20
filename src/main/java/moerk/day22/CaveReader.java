package moerk.day22;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import moerk.Util;

import java.awt.Point;
import java.util.List;

/**
 * @author matthias
 */
public class CaveReader {
	public Cave read() {
		List<String> lines = Util.lines( "day22/prescantest3.txt" );

		Table<Integer, Integer, Region> map = HashBasedTable.create();
		Point target = null;
		for ( int y = 0; y < lines.size(); y++ ) {
			String line = lines.get( y );
			for ( int x = 0; x < line.length(); x++ ) {
				char c = line.charAt( x );
				if ( c == 'X' || c == '.' ) {
					map.put( x, y, new Region( 0, x, y ) );
				}
				else if ( c == '=' ) {
					map.put( x, y, new Region( 1, x, y ) );
				}
				else if ( c == '|' ) {
					map.put( x, y, new Region( 2, x, y ) );
				}
				else if ( c == 'T' ) {
					target = new Point( x, y );
					map.put( x, y, new Region( 0, x, y ) );
				}
			}
		}

		return new Cave( map, target );
	}
}
package moerk.day17;

import moerk.Util;

import java.util.List;

/**
 * @author matthias
 */
public class ScanReader {

	public Scan read() {
		Scan scan = new Scan();
		RangeParser rangeParser = new RangeParser();

		Util.lines( "day17/scan.txt" )
			.stream()
			.map( rangeParser::parse )
			.flatMap( List::stream )
			.forEach( p -> scan.set( p.x, p.y, Type.CLAY ) );

		return scan;
	}
}
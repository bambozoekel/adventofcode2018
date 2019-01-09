package moerk.day18;

import moerk.Util;

import java.util.List;
import java.util.Map;

/**
 * @author matthias
 */
public class AreaReader {
	private static final Map<String, Type> TYPEMAP = Map.of(
		"#", Type.LUMBERYARD,
		"|", Type.TREES,
		".", Type.OPEN
	);

	public Area read() {
		List<String> lines = Util.lines( "day18/area.txt" );

		Area area = new Area( lines.get( 0 ).length(), lines.size() );
		for ( int y = 0; y < lines.size(); y++ ) {
			String line = lines.get( y );
			for ( int x = 0; x < line.length(); x++ ) {
				area.set( x, y, TYPEMAP.get( line.substring( x, x + 1 ) ) );
			}
		}

		area.commit();
		return area;
	}
}
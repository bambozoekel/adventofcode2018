package moerk.day17;

import java.util.Map;

/**
 * @author matthias
 */
public class ScanPrinter {
	private static final Map<Type, String> TYPEMAP = Map.of(
		Type.SAND, ".",
		Type.CLAY, "#",
		Type.WATER, "~",
		Type.FLOW, "|",
		Type.SOURCE, "+"
	);

	public void print( Scan scan ) {
		for ( int y = 0; y <= scan.getMaxY(); y++ ) {
			for ( int x = scan.getMinX() - 1; x <= scan.getMaxX() + 1; x++ ) {
				System.out.print( TYPEMAP.get( scan.get( x, y ) ) );
			}
			System.out.println();
		}
	}
}
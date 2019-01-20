package moerk.day22.puzzle1;

import moerk.day22.Cave;
import moerk.day22.Scan;
import moerk.day22.ScanReader;

/**
 * @author matthias
 */
public class RiskCalculator {
	public static void main( String[] args ) {
		Scan scan = new ScanReader().read();
		Cave cave = new Cave( scan );

		int risk = 0;
		for ( int y = 0; y <= cave.getHeight(); y++ ) {
			for ( int x = 0; x <= cave.getWidth(); x++ ) {
				risk += cave.get( x, y ).getType().getRisk();
			}
		}

		System.out.println( risk );
	}
}
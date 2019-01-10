package moerk.day15;

import moerk.Util;

import java.util.List;

/**
 * @author matthias
 */
public class GrottoReader {
	public Grotto read( EntityFactory factory ) {
		List<String> lines = Util.lines( "day15/map.txt" );

		Grotto grotto = new Grotto( lines.get( 0 ).length(), lines.size() );

		for ( int y = 0; y < lines.size(); y++ ) {
			String line = lines.get( y );

			for ( int x = 0; x < line.length(); x++ ) {
				String s = line.substring( x, x + 1 );
				grotto.set( x, y, factory.create( s ) );
			}
		}

		return grotto;
	}
}
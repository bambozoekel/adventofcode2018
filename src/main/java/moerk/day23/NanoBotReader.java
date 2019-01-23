package moerk.day23;

import moerk.Util;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author matthias
 */
public class NanoBotReader {
	private static final Pattern PATTERN = Pattern.compile( "pos=<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(\\d+)" );

	public Collection<NanoBot> read() {
//		return Util.read( "day23/puzzle2/test.txt", this::parse );
		return Util.read( "day23/bots.txt", this::parse );
	}

	private NanoBot parse( String line ) {
		Matcher matcher = PATTERN.matcher( line );
		if ( matcher.matches() ) {
			int x = Integer.parseInt( matcher.group( 1 ) );
			int y = Integer.parseInt( matcher.group( 2 ) );
			int z = Integer.parseInt( matcher.group( 3 ) );
			int radius = Integer.parseInt( matcher.group( 4 ) );

			return new NanoBot( new Point( x, y, z ), radius );
		}
		else {
			throw new IllegalArgumentException( line + " does not match" );
		}
	}
}
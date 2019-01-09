package moerk.day9;

import moerk.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author matthias
 */
public class SettingsReader {
	private static final Pattern PATTERN = Pattern.compile( "(\\d+) players; last marble is worth (\\d+) points" );

	public Settings read() {
		String s = Util.lines( "day9/settings.txt" ).get( 0 );
		Matcher matcher = PATTERN.matcher( s );
		if ( matcher.matches() ) {
			int players = Integer.parseInt( matcher.group(1));
			int marbles = Integer.parseInt(matcher.group(2));
			return new Settings(players,marbles);
		}
		else {
			throw new RuntimeException("Unable to read" );
		}
	}
}
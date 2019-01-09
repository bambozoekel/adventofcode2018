package moerk.day12;

import moerk.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author matthias
 */
public class SituationReader {
	public Situation read() {
		List<String> lines = Util.lines("day12/plants.txt");
		String initialState = lines.get(0).substring( 15 );

		Map<String, String> rules = new HashMap<>();

		lines.stream()
			.skip( 2 )
			.forEach( l -> rules.put( l.substring( 0, 5 ), l.substring( 9 ) ) );

		return new Situation( initialState, rules );
	}
}
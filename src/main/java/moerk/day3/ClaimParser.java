package moerk.day3;

import moerk.Util;

import java.awt.Rectangle;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class ClaimParser {
	private static Pattern pattern = Pattern.compile("(#\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");

	public List<Claim> parseClaims() {
		return Util.lines("day3/puzzle1/claims.txt")
			.stream()
			.map( this::parseClaim)
			.collect( Collectors.toList());
	}

	private Claim parseClaim(String line) {
		Matcher m = pattern.matcher( line );
		if (m.matches()) {
			int x = Integer.parseInt(m.group(2));
			int y = Integer.parseInt(m.group(3));
			int w = Integer.parseInt(m.group(4));
			int h = Integer.parseInt(m.group(5));
			return new Claim( m.group(1), new Rectangle( x, y, w, h));
		}
		else {
			throw new RuntimeException(line);
		}
	}
}
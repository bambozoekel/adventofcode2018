package moerk.day17;

import java.awt.Point;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author matthias
 */
public class RangeParser {
	private static final Pattern PATTERN = Pattern.compile( "([xy])=(\\d+), [xy]=(\\d+)..(\\d+)" );

	public List<Point> parse( String input ) {
		Matcher matcher = PATTERN.matcher( input );
		if ( matcher.matches() ) {
			String coordinate = matcher.group( 1 );
			int single = Integer.parseInt( matcher.group( 2 ) );
			int start = Integer.parseInt( matcher.group( 3 ) );
			int end = Integer.parseInt( matcher.group( 4 ) );

			if ( coordinate.equals( "x" ) ) {
				return createFromX( single, start, end );
			}
			else {
				return createFromY( single, start, end );
			}
		}
		else {
			throw new RuntimeException();
		}
	}

	private List<Point> createFromX( int x, int startY, int endY ) {
		return IntStream.range( startY, endY + 1 )
			.mapToObj( y -> new Point( x, y ) )
			.collect( Collectors.toList() );
	}

	private List<Point> createFromY( int y, int startX, int endX ) {
		return IntStream.range( startX, endX + 1 )
			.mapToObj( x -> new Point( x, y ) )
			.collect( Collectors.toList() );
	}
}
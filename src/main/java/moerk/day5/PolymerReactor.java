package moerk.day5;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author matthias
 */
public class PolymerReactor {
	public int react( List<Integer> chars ) {

		int reactPosition = 0;
		while ( (reactPosition = findReactPosition(chars, Math.max(0,reactPosition -1))) > -1) {
			chars.remove( reactPosition );
			chars.remove( reactPosition );
		}

		return chars.size();
	}

	private static int findReactPosition( List<Integer> chars, int start ) {
		return IntStream.range( start, chars.size() - 1 )
			.filter( i -> Math.abs( chars.get( i ) - chars.get( i + 1)) == 32 )
			.findFirst()
			.orElse(-1);
	}
}
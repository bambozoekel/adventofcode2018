package moerk.day23.puzzle1;

import moerk.day23.NanoBot;
import moerk.day23.NanoBotReader;

import java.util.Collection;
import java.util.Comparator;

/**
 * @author matthias
 */
public class BiggestRange {
	public static void main( String[] args ) {
		Collection<NanoBot> bots = new NanoBotReader().read();

		NanoBot biggestRange = bots.stream().max( Comparator.comparing( NanoBot::getRadius ) ).orElseThrow();

		long inRange = bots.stream()
			.filter( bot -> bot.getDistance( biggestRange ) <= biggestRange.getRadius() )
			.count();

		System.out.println( inRange );
	}
}
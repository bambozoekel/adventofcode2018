package moerk.day9.puzzle1;

import moerk.day9.Circle;
import moerk.day9.Settings;
import moerk.day9.SettingsReader;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author matthias
 */
public class ElveGame {
	public static void main( String[] args ) {
		long start = System.currentTimeMillis();
		Settings settings = new SettingsReader().read();
		Circle circle = new Circle();
		Map<Integer, Long> scores = new HashMap<>();

		int player = 1;
		for ( int marble = 1; marble <= settings.getMarbles(); marble++ ) {
			if ( marble % 23 == 0 ) {
				long score = circle.removeLeft( 7 ) + marble;
				scores.merge(player, score, Long::sum);
			}
			else {
				circle.addRight( 2, marble );
			}

			player++;
			if ( player > settings.getPlayers() ) {
				player = 1;
			}
		}

		scores.values()
			.stream()
			.max( Comparator.naturalOrder() )
			.ifPresent( System.out::println );

		long stop = System.currentTimeMillis();
		System.out.println(stop-start);
	}
}
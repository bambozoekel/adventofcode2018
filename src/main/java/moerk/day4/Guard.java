package moerk.day4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author matthias
 */
public class Guard {
	private final int id;
	private Map<Integer, Integer> sleepMinutes = new HashMap<>();

	public Guard( int id ) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void addSleepTime( int start, int stop ) {
		for ( int i = start; i < stop; i++ ) {
			sleepMinutes.merge(i, 1, Integer::sum);
		}
	}

	public int getTotalSleepTime() {
		return sleepMinutes.values().stream().reduce(0, Integer::sum);
	}

	public int getMaximumAmountOfSleepInMinute() {
		return sleepMinutes.values().stream().max( Comparator.naturalOrder() ).orElse(0);
	}

	public int getMinuteWithMostSleep() {
		return sleepMinutes.entrySet()
			.stream()
			.max( Comparator.comparing( Map.Entry::getValue ) )
			.map( Map.Entry::getKey )
			.orElseThrow(RuntimeException::new);
	}
}
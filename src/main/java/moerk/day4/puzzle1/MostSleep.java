package moerk.day4.puzzle1;

import moerk.day4.Guard;
import moerk.day4.GuardSleepDetector;

import java.util.Comparator;

/**
 * @author matthias
 */
public class MostSleep {
	public static void main( String[] args ) {
		new GuardSleepDetector().detectSleep()
			.stream()
			.max( Comparator.comparing( Guard::getTotalSleepTime ) )
			.ifPresent( g -> System.out.println( g.getId() * g.getMinuteWithMostSleep() )) ;
	}
}
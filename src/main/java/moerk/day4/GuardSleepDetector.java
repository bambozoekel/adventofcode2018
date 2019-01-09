package moerk.day4;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author matthias
 */
public class GuardSleepDetector {
	public Collection<Guard> detectSleep() {
		Map<Integer, Guard> guards = new HashMap<>();
		List<Event> events = new EventParser().parse();
		events.sort( Comparator.comparing(Event::getTime));

		Guard currentGuard = null;
		int startSleep = 0;
		int stopSleep;
		for (Event event : events) {
			if (event.getType() == Event.Type.START_SHIFT) {
				currentGuard = guards.computeIfAbsent( event.getGuardId(), Guard::new );
			}
			else if (event.getType() == Event.Type.FALL_ASLEEP) {
				startSleep = event.getTime().getMinute();
			}
			else if ( event.getType() == Event.Type.WAKE_UP) {
				stopSleep = event.getTime().getMinute();
				currentGuard.addSleepTime( startSleep, stopSleep );
			}
		}

		return guards.values();
	}
}
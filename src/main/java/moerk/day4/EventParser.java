package moerk.day4;

import moerk.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class EventParser {
	private static final Pattern pattern = Pattern.compile("\\[(.+?)\\] (?:(?:Guard #(\\d+) begins shift)|(falls asleep)|(wakes up))");
	private static DateTimeFormatter format = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm" );

	public List<Event> parse() {
		return Util.lines("day4/events.txt")
			.stream()
			.map(this::parse)
			.collect( Collectors.toList());
	}

	private Event parse(String line) {
		Matcher m = pattern.matcher( line );
		if (m.matches()) {
			LocalDateTime time = format.parse( m.group(1), LocalDateTime::from );
			if ( m.group(2) != null) {
				return new Event( time, Integer.parseInt(m.group(2)));
			}
			else if (m.group(3) != null) {
				return new Event(time,Event.Type.FALL_ASLEEP);
			}
			else if (m.group(4) != null){
				return new Event(time,Event.Type.WAKE_UP);
			}
			else {
				throw new RuntimeException("Stuk: " + line);
			}
		}
		else {
			throw new RuntimeException(line);
		}
	}
}
package moerk.day4;

import java.time.LocalDateTime;

/**
 * @author matthias
 */
public class Event {
	public enum Type { START_SHIFT, FALL_ASLEEP, WAKE_UP }

	private final LocalDateTime time;
	private int guardId;
	private final Type type;

	public Event( LocalDateTime time, int guardId ) {
		this.time = time;
		this.guardId = guardId;
		type = Type.START_SHIFT;
	}

	public Event( LocalDateTime time, Type type ) {
		this.time = time;
		this.type = type;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public int getGuardId() {
		return guardId;
	}

	public Type getType() {
		return type;
	}
}
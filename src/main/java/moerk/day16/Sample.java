package moerk.day16;

import java.util.List;

/**
 * @author matthias
 */
public class Sample {
	private final List<Integer> before;
	private final List<Integer> instruction;
	private final List<Integer> after;

	public Sample( List<Integer> before, List<Integer> instruction, List<Integer> after ) {
		this.before = before;
		this.instruction = instruction;
		this.after = after;
	}

	public List<Integer> getBefore() {
		return before;
	}

	public List<Integer> getInstruction() {
		return instruction;
	}

	public List<Integer> getAfter() {
		return after;
	}
}
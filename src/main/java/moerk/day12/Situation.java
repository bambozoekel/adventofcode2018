package moerk.day12;

import java.util.Map;

/**
 * @author matthias
 */
public class Situation {
	private final String initalState;
	private final Map<String, String> rules;

	public Situation( String initalState, Map<String, String> rules ) {
		this.initalState = initalState;
		this.rules = rules;
	}

	public String getInitalState() {
		return initalState;
	}

	public Map<String, String> getRules() {
		return rules;
	}
}
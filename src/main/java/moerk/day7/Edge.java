package moerk.day7;

/**
 * @author matthias
 */
public class Edge {
	private final String from;
	private final String to;

	public Edge( String from, String to ) {
		this.from = from;
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}
}
package moerk.day7;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author matthias
 */
public class Node {
	private final String letter;
	private final Set<Node> before;
	private final Set<Node> after;

	public Node( String letter ) {
		this.letter = letter;
		before = new HashSet<>();
		after = new HashSet<>();
	}

	public String getLetter() {
		return letter;
	}

	public void addBefore( Node node ) {
		before.add( node );
	}

	public void addAfter( Node node ) {
		after.add( node );
	}

	public Set<Node> getBefore() {
		return Collections.unmodifiableSet( before );
	}

	public Set<Node> getAfter() {
		return after;
	}

	public boolean beforeAreDone( StringBuilder letters ) {
		return before.stream()
			.allMatch( node -> letters.indexOf( node.getLetter() ) > -1 );
	}
}
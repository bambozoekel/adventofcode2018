package moerk.day7;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author matthias
 */
public class GraphBuilder {
	public Set<Node> build( Collection<Edge> edges ) {
		Map<String, Node> nodes = new HashMap<>();

		for ( Edge edge : edges ) {
			Node before = nodes.computeIfAbsent( edge.getFrom(), Node::new );
			Node after = nodes.computeIfAbsent( edge.getTo(), Node::new );

			before.addAfter( after );
			after.addBefore( before );
		}

		return nodes.values()
			.stream()
			.filter( node -> node.getBefore().isEmpty() )
			.collect( Collectors.toSet() );
	}
}
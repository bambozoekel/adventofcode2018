package moerk.day7.puzzle1;

import moerk.day7.Edge;
import moerk.day7.EdgeReader;
import moerk.day7.GraphBuilder;
import moerk.day7.Node;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author matthias
 */
public class SleighBuilder {
	public static void main( String[] args ) {
		long start = System.currentTimeMillis();
		Set<Edge> edges = new EdgeReader().read();
		Set<Node> firstNodes = new GraphBuilder().build( edges );

		SortedSet<Node> available = new TreeSet<>( Comparator.comparing( Node::getLetter ) );
		available.addAll( firstNodes );

		StringBuilder letters = new StringBuilder();

		while (!available.isEmpty()) {
			for ( Node node : available ) {
				if ( node.beforeAreDone( letters ) ) {
					letters.append( node.getLetter() );
					available.addAll( node.getAfter() );
					available.remove( node );
					break;
				}
			}
		}

		long stop = System.currentTimeMillis();
		System.out.println(letters);
		System.out.println(stop - start);
	}
}
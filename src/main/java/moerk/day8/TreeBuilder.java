package moerk.day8;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author matthias
 */
public class TreeBuilder {
	public Node build() {
		Queue<Integer> data = new LinkedList<>( new NodeDataReader().read() );
		return buildTree( data );
	}

	private Node buildTree( Queue<Integer> data ) {
		Node node = new Node();
		int childCount = data.poll();
		int metadataCount = data.poll();
		for ( int i = 0; i < childCount; i++ ) {
			node.addChild( buildTree( data ) );
		}

		for ( int i = 0; i < metadataCount; i++ ) {
			node.addMetadata( data.poll() );
		}

		return node;
	}
}
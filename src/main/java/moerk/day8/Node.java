package moerk.day8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author matthias
 */
public class Node {
	private List<Node> children;
	private List<Integer> metadata;

	public Node() {
		children = new ArrayList<>();
		metadata = new ArrayList<>();
	}

	public List<Node> getChildren() {
		return children;
	}

	public void addChild( Node node ) {
		children.add( node );
	}

	public void addMetadata( Integer metadata ) {
		this.metadata.add( metadata );
	}

	public int getMetadataSum() {
		return sumMetadata() + children.stream().map(Node::getMetadataSum).reduce(0,Integer::sum);
	}

	private int sumMetadata() {
		return metadata.stream().reduce(0, Integer::sum);
	}

	public int getValue() {
		if (children.isEmpty()) {
			return sumMetadata();
		}
		else {
			return metadata.stream()
				.filter( m -> m > 0 && m <= children.size() )
				.map( i -> children.get( i - 1 ) )
				.map(Node::getValue)
				.reduce( 0, Integer::sum );
		}
	}
}
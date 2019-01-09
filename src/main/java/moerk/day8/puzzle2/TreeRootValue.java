package moerk.day8.puzzle2;

import moerk.day8.Node;
import moerk.day8.TreeBuilder;

/**
 * @author matthias
 */
public class TreeRootValue {
	public static void main( String[] args ) {
		long start = System.currentTimeMillis();

		Node root = new TreeBuilder().build();

		long stop = System.currentTimeMillis();
		System.out.println(root.getValue());
		System.out.println(stop - start);

	}
}
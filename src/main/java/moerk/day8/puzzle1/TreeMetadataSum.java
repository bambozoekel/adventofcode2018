package moerk.day8.puzzle1;

import moerk.day8.Node;
import moerk.day8.TreeBuilder;

/**
 * @author matthias
 */
public class TreeMetadataSum {
	public static void main( String[] args ) {
		long start = System.currentTimeMillis();

		Node root = new TreeBuilder().build();
		int sum = root.getMetadataSum();

		long stop = System.currentTimeMillis();
		System.out.println(sum);
		System.out.println(stop - start);
	}
}
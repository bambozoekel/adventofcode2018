package moerk.day22.puzzle2;

import moerk.day22.Cave;
import moerk.day22.CaveReader;
import moerk.day22.Gear;
import moerk.day22.Node;
import moerk.day22.Region;
import moerk.day22.Scan;
import moerk.day22.ScanReader;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author matthias
 */
public class ShortestPath {
	public static void main( String[] args ) {
		// 1032 too high
		// 1014 too high
		// 979, incorrect
		// 997, incorrect
		Scan scan = new ScanReader().read();
		Cave cave = new Cave( scan, 100 );
//		Cave cave = new CaveReader().read();
		Region start = cave.get( 0, 0 );
		start.initialize( Gear.TORCH, 0 );


		Set<Node> queue = new HashSet<>();
		queue.add( start.getNode( Gear.TORCH ) );

		Set<Node> beenThere = new HashSet<>();

		while ( !queue.isEmpty() ) {
			Node node = queue.stream().min( Comparator.comparing( Node::getValue ) ).orElseThrow();
			queue.remove( node );

			Map<Node, Integer> edges = cave.getNeighbours( node );

			for ( Map.Entry<Node, Integer> entry : edges.entrySet() ) {
				Node neighbour = entry.getKey();
				Integer value = entry.getValue();
				neighbour.updateValue( node.getValue() + value );
				if ( !beenThere.contains( neighbour ) ) {
					queue.add( neighbour );
				}
			}

			beenThere.add( node );
		}

		System.out.println( cave.get( cave.getTarget().x, cave.getTarget().y ) );
	}
}
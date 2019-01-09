package moerk.day7.puzzle2;

import moerk.day7.Edge;
import moerk.day7.EdgeReader;
import moerk.day7.GraphBuilder;
import moerk.day7.Node;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.ToIntFunction;

/**
 * @author matthias
 */
public class ParallelSleighBuilder {
	public static void main( String[] args ) {
		long start = System.currentTimeMillis();
		Set<Edge> edges = new EdgeReader().read();
		Set<Node> firstNodes = new GraphBuilder().build( edges );

		SortedSet<Node> available = new TreeSet<>( Comparator.comparing( Node::getLetter ) );
		available.addAll( firstNodes );

		StringBuilder letters = new StringBuilder();
		int seconds = 0;
		List<Worker> workers = Arrays.asList( new Worker(), new Worker(), new Worker(), new Worker(), new Worker() );
		ToIntFunction<String> timeForWork = s -> s.charAt(0) - 4;

		while (!available.isEmpty() || workers.stream().anyMatch( w -> !w.isIdle() )) {
			//Verdeel available nodes onder workers die idle zijn
			for ( Iterator<Node> i = available.iterator(); i.hasNext();) {
				Node work = i.next();
				if ( work.beforeAreDone( letters ) ) {
					for (Worker worker : workers) {
						if (worker.isIdle()) {
							worker.beginWork( work, timeForWork.applyAsInt(work.getLetter()) );
							i.remove();
							break;
						}
					}
				}
			}

			seconds++;
			//voor alle niet idle workers doWork()
			workers.stream()
				.filter(w -> !w.isIdle())
				.forEach( Worker::doWork );

			//Voeg van alle workers die niet working zijn de letters toe aan letters
			workers.stream()
				.filter(w -> !w.isIdle() && w.isDone())
				.map(Worker::deliverwork)
				.sorted( Comparator.comparing( Node::getLetter))
				.forEach(node -> { letters.append( node.getLetter() ); available.addAll(node.getAfter());});
		}

		long stop = System.currentTimeMillis();
		System.out.println(letters);
		System.out.println(seconds);
		System.out.println(stop - start);

	}
}
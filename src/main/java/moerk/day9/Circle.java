package moerk.day9;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author matthias
 */
public class Circle {
	private final Deque<Integer> marbles;

	public Circle() {
		marbles = new LinkedList<>();
		marbles.add(0);
	}

	public void addRight(int index, int marble) {
		for ( int i = 0; i < index; i++ ) {
			marbles.addLast(marbles.removeFirst());
		}

		marbles.addFirst(marble);
	}

	public int removeLeft(int index) {
		for ( int i = 0; i < index - 1; i++ ) {
			marbles.addFirst(marbles.removeLast());
		}

		return marbles.removeLast();
	}

	@Override
	public String toString() {
		return marbles.toString();
	}
}
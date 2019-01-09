package moerk.day7.puzzle2;

import moerk.day7.Node;

/**
 * @author matthias
 */
public class Worker {
	private Node work;
	private int timeLeft;

	public void beginWork(Node work, int time) {
		this.work = work;
		timeLeft = time;
	}

	public void doWork() {
		timeLeft--;
	}

	public boolean isDone() {
		return timeLeft == 0;
	}

	public Node deliverwork() {
		Node doneWork = work;
		work = null;
		return doneWork;
	}

	public boolean isIdle() {
		return work == null;
	}
}
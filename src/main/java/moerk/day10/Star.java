package moerk.day10;

/**
 * @author matthias
 */
public class Star {
	private final int startx;
	private final int starty;
	private final int dvx;
	private final int dvy;
	private int x;
	private int y;

	public Star( int startx, int starty, int dvx, int dvy ) {
		this.startx = startx;
		this.starty = starty;
		this.dvx = dvx;
		this.dvy = dvy;
		x = startx;
		y = starty;
	}

	public void moveForward() {
		x += dvx;
		y += dvy;
	}

	public void moveBackward() {
		x -= dvx;
		y -= dvy;
	}

	public void jumpTo( int t ) {
		x = startx + t * dvx;
		y = starty + t * dvy;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getT() {
		return (x - startx) / dvx;
	}
}
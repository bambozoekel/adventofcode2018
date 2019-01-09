package moerk.day13;

/**
 * @author matthias
 */
public class CollisionException extends RuntimeException {
	private final Cart blocking;
	private final Cart moving;

	public CollisionException( Cart blocking, Cart moving ) {
		this.blocking = blocking;
		this.moving = moving;
	}

	public Cart getBlocking() {
		return blocking;
	}

	public Cart getMoving() {
		return moving;
	}
}
package moerk.day13;

/**
 * @author matthias
 */
public class TrackInUseException extends RuntimeException {
	private final Cart cart;

	public TrackInUseException( Cart cart ) {
		this.cart = cart;
	}

	public Cart getCart() {
		return cart;
	}
}
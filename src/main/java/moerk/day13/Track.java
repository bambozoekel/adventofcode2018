package moerk.day13;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @author matthias
 */
public class Track {
	private Table<Integer, Integer, TrackPart> trackParts;
	private Set<Cart> carts;

	private Track( Builder builder ) {
		trackParts = builder.trackParts;
		carts = builder.carts;
	}

	public TrackPart get( int x, int y ) {
		carts.stream()
			.filter( c -> c.isOnPosition( x, y ) )
			.findFirst()
			.ifPresent( c -> { throw new TrackInUseException( c ); } );

		return trackParts.get( x, y );
	}

	public void tick() {
		carts.stream()
			.sorted( Comparator.comparing( Cart::getY ).thenComparing( Comparator.comparing( Cart::getX ) ) )
			.forEach( cart -> cart.moveForward( this ) );
	}

	public void removeCart( Cart cart ) {
		carts.remove( cart );
	}

	public Set<Cart> getCarts() {
		return carts;
	}

	public static class Builder {
		private final Table<Integer, Integer, TrackPart> trackParts;
		private final Set<Cart> carts;

		public Builder() {
			trackParts = HashBasedTable.create();
			carts = new HashSet<>();
		}

		public void addPart( int x, int y, TrackPart part ) {
			trackParts.put( x, y, part );
		}

		public void addCart( Cart cart ) {
			carts.add( cart );
		}

		public Track build() {
			return new Track( this );
		}
	}
}
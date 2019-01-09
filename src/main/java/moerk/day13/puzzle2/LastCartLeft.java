package moerk.day13.puzzle2;

import moerk.day13.Cart;
import moerk.day13.CollisionException;
import moerk.day13.Track;
import moerk.day13.TrackReader;

/**
 * @author matthias
 */
public class LastCartLeft {
	public static void main( String[] args ) {
		long start = System.currentTimeMillis();
		Track track = new TrackReader().read();

		int i = 0;
		while( track.getCarts().size() > 1 ) {
			try {
				track.tick();
				i++;
			}
			catch( CollisionException e ) {
				track.removeCart( e.getBlocking() );
				track.removeCart( e.getMoving() );
			}
		}

		Cart cart = track.getCarts().iterator().next();
		System.out.println( cart.getX() + "," + cart.getY() );
		long stop = System.currentTimeMillis();
		System.out.println(stop-start);
	}
}
package moerk.day13.puzzle1;

import moerk.day13.CollisionException;
import moerk.day13.Track;
import moerk.day13.TrackReader;

/**
 * @author matthias
 */
public class FirstCollision {
	public static void main( String[] args ) {
		long start = System.currentTimeMillis();
		Track track = new TrackReader().read();

		int i = 0;
		boolean ticking = true;
		while( ticking ) {
			try {
				track.tick();
				i++;
			}
			catch( CollisionException e ) {
				System.out.println( e.getBlocking().getX() + "," + e.getBlocking().getY() );
				System.out.println( i );
				ticking = false;
			}
		}

		long stop = System.currentTimeMillis();
		System.out.println(stop-start);
	}
}
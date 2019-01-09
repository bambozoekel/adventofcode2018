package moerk.day13;

import com.google.common.collect.Iterators;

import java.awt.Point;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * @author matthias
 */
public class Cart {
	private static final List<Function<Direction, Direction>> INTERSECTION_CHOICES = List.of(
		Direction::turnLeft,
		Direction::straight,
		Direction::turnRight
	);

	private int x;
	private int y;
	private Direction direction;
	private Iterator<Function<Direction, Direction>> intersectionChoices;

	public Cart( int x, int y, Direction direction ) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		intersectionChoices = Iterators.cycle( INTERSECTION_CHOICES );
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Direction getDirection() {
		return direction;
	}

	public void moveForward( Track track ) {
		Point p = direction.move( x, y );
		try {
			TrackPart trackPart = track.get( p.x, p.y );
			direction = trackPart.turn( this );
			x = p.x;
			y = p.y;
		}
		catch( TrackInUseException e ) {
			throw new CollisionException( e.getCart(), this );
		}
	}

	public void moveForward2( Track track ) {
		Point p = direction.move( x, y );
		try {
			TrackPart trackPart = track.get( p.x, p.y );
			direction = trackPart.turn( this );
			x = p.x;
			y = p.y;
		}
		catch( TrackInUseException e ) {
			track.removeCart( this );
			track.removeCart( e.getCart() );
		}
	}

	public Direction chooseDirection() {
		return intersectionChoices.next().apply( direction );
	}

	public boolean isOnPosition( int x, int y ) {
		return this.x == x && this.y == y;
	}
}
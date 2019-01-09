package moerk.day13;

import com.google.common.collect.ImmutableMap;

import java.awt.Point;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author matthias
 */
public enum Direction {
	UP( "^", Function.identity(), y -> y - 1 ),
	DOWN( "v", Function.identity(), y -> y + 1 ),
	LEFT( "<", x -> x - 1, Function.identity() ),
	RIGHT( ">", x -> x + 1, Function.identity() );

	private static final Map<Direction, Direction> TURN_LEFT = new ImmutableMap.Builder<Direction, Direction>()
		.put( UP, LEFT )
		.put( DOWN, RIGHT )
		.put( LEFT, DOWN )
		.put( RIGHT, UP )
		.build();

	private static final Map<Direction, Direction> TURN_RIGHT = new ImmutableMap.Builder<Direction, Direction>()
		.put( UP, RIGHT )
		.put( DOWN, LEFT )
		.put( LEFT, UP )
		.put( RIGHT, DOWN )
		.build();

	private String label;
	private Function<Integer, Integer> dx;
	private Function<Integer, Integer> dy;

	Direction( String label, Function<Integer, Integer> dx, Function<Integer, Integer> dy ) {
		this.label = label;
		this.dx = dx;
		this.dy = dy;
	}

	public Point move( int x, int y ) {
		return new Point( dx.apply( x ), dy.apply( y ) );
	}

	public Direction turnLeft() {
		return TURN_LEFT.get( this );
	}

	public Direction turnRight() {
		return TURN_RIGHT.get( this );
	}

	public Direction straight() {
		return this;
	}

	public static Optional<Direction> fromLabel( String label ) {
		for ( Direction direction : values() ) {
			if ( direction.label.equals( label ) ) {
				return Optional.of( direction );
			}
		}

		return Optional.empty();
	}
}
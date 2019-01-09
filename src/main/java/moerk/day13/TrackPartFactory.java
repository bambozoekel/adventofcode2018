package moerk.day13;

import java.util.Map;
import java.util.Optional;

/**
 * @author matthias
 */
public class TrackPartFactory {
	private static final Map<String, TrackPart> PARTS = Map.of(
		"-", Cart::getDirection,
		"|", Cart::getDirection,
		"+", Cart::chooseDirection,
		"/", new TopLeftBottomRight(),
		"\\", new TopRightBottomLeft()
	);

	public Optional<TrackPart> create( String part ) {
		return Optional.ofNullable( PARTS.get( part ) );
	}

	public TrackPart asTrackPart( Direction direction ) {
		if ( direction == Direction.UP || direction == Direction.DOWN ) {
			return create( "-" ).orElseThrow();
		}
		else {
			return create( "|" ).orElseThrow();
		}
	}

	private static class TopLeftBottomRight implements TrackPart {
		private static final Map<Direction, Direction> DIRECTIONS = Map.of(
			Direction.RIGHT, Direction.UP,
			Direction.LEFT, Direction.DOWN,
			Direction.DOWN, Direction.LEFT,
			Direction.UP, Direction.RIGHT
		);

		@Override
		public Direction turn( Cart cart ) {
			return DIRECTIONS.get( cart.getDirection() );
		}
	}

	private static class TopRightBottomLeft implements TrackPart {
		private static final Map<Direction, Direction> DIRECTIONS = Map.of(
			Direction.RIGHT, Direction.DOWN,
			Direction.LEFT, Direction.UP,
			Direction.DOWN, Direction.RIGHT,
			Direction.UP, Direction.LEFT
		);

		@Override
		public Direction turn( Cart cart ) {
			return DIRECTIONS.get( cart.getDirection() );
		}
	}
}
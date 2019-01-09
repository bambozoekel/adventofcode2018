package moerk.day13;

/**
 * @author matthias
 */
@FunctionalInterface
public interface TrackPart {
	Direction turn( Cart cart );
}
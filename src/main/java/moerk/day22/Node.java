package moerk.day22;

/**
 * @author matthias
 */
public class Node {
	private final Region region;
	private final Gear gear;
	private int value;

	public Node( Region region, Gear gear ) {
		this.region = region;
		this.gear = gear;
		value = Integer.MAX_VALUE;
	}

	public void updateValue( int newValue ) {
		value = Math.min( value, newValue );
	}

	public int getValue() {
		return value;
	}

	public Node getOther() {
		return region.getNode( region.getType().otherGear( gear ) );
	}

	public Region getRegion() {
		return region;
	}

	public Gear getGear() {
		return gear;
	}

	@Override
	public String toString() {
		return gear + ": " + Integer.toString( value );
	}
}
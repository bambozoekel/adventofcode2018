package moerk.day22;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author matthias
 */
public class Region {
	private final int x;
	private final int y;
	private final Type type;
	private final long erosionLevel;
	private final Map<Gear, Node> nodes;

	public Region( long erosionLevel, int x, int y ) {
		this.x = x;
		this.y = y;
		type = Type.of( erosionLevel );
		this.erosionLevel = erosionLevel;

		nodes = new EnumMap<>( Gear.class );
		type.getGear().forEach( gear -> nodes.put( gear, new Node( this, gear ) ) );
	}

	public void initialize( Gear gear, int initialValue ) {
		nodes.get( gear ).updateValue( initialValue );
	}

	public Node getNode( Gear gear ) {
		return nodes.get( gear );
	}

	public Type getType() {
		return type;
	}

	public long getErosionLevel() {
		return erosionLevel;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ") "  + type + ", " + nodes;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
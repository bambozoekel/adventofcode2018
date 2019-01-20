package moerk.day22;

import java.util.List;

/**
 * @author matthias
 */
public enum Type {
	ROCK(0, Gear.TORCH, Gear.CLIMBING ),
	WET(1, Gear.NONE, Gear.CLIMBING ),
	NARROW(2, Gear.NONE, Gear.TORCH );

	private int risk;
	private List<Gear> gear;

	Type( int risk, Gear gear1, Gear gear2 ) {
		this.risk = risk;
		gear = List.of( gear1, gear2 );
	}

	public int getRisk() {
		return risk;
	}

	public static Type of( long erosionLevel ) {
		return values()[ (int)(erosionLevel % 3) ];
	}

	public List<Gear> getGear() {
		return gear;
	}

	public Gear otherGear( Gear gear ) {
		return this.gear.get( Math.abs( this.gear.indexOf( gear ) - 1 ) );
	}
}
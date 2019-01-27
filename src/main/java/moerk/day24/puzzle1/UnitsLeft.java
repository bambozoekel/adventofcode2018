package moerk.day24.puzzle1;

import moerk.day24.Battle;
import moerk.day24.Party;
import moerk.day24.PartyReader;

/**
 * @author matthias
 */
public class UnitsLeft {
	public static void main( String[] args ) {
		//26175 too low
		//26277

		PartyReader reader = new PartyReader();

		Party immuneSystem = reader.readImmuneSystem();
		Party infection = reader.readInfection();

		Party winningParty = new Battle().fight( immuneSystem, infection );
		System.out.println( winningParty.getUnits() );
	}
}
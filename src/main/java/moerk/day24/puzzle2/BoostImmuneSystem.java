package moerk.day24.puzzle2;

import moerk.day24.Battle;
import moerk.day24.Party;
import moerk.day24.PartyReader;

/**
 * @author matthias
 */
public class BoostImmuneSystem {
	public static void main( String[] args ) {
		//boost = 29 (te laag) tm 39
		PartyReader reader = new PartyReader();

		int lowerBound = 29;
		int upperBound = 39;
		int boost = 35;

		Party immuneSystem = null;
		while ( upperBound - lowerBound != 1 ) {
			immuneSystem = reader.readImmuneSystem();
			Party infection = reader.readInfection();

			immuneSystem.setBoost( boost );
			Party winningParty = new Battle().fight( immuneSystem, infection );
			System.out.println( "Boost: " + boost + " -> " + winningParty.getName() + ": " + winningParty.getUnits() );

			if ( winningParty == immuneSystem ) {
				upperBound = boost;
				boost = lowerBound + (upperBound - lowerBound) / 2;
			}
			else if ( winningParty == infection ) {
				lowerBound = boost;
				boost = lowerBound + (upperBound - lowerBound) / 2;
			}

			System.out.println( boost + ": (" + lowerBound + "," + upperBound + ")" );
		}




		System.out.println( boost + ": " + immuneSystem.getUnits() );
	}
}
package moerk.day24.puzzle1;

import moerk.day24.Fight;
import moerk.day24.Party;
import moerk.day24.PartyReader;

import java.util.Comparator;

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

		FightAssembler assembler = new FightAssembler( immuneSystem, infection );

		while ( immuneSystem.hasUnitsLeft() && infection.hasUnitsLeft() ) {
			assembler.assemble()
				.stream()
				.sorted( Comparator.comparing( Fight::getAttackerInitiative ).reversed() )
				.forEach( Fight::fight );
		}

		immuneSystem.getGroups().forEach( g -> System.out.println( g.getIndex() + ": " + g.getUnits() ) );
		System.out.println( immuneSystem.getUnits() );
		infection.getGroups().forEach( g -> System.out.println( g.getIndex() + ": " + g.getUnits() ) );
		System.out.println( infection.getUnits() );
	}
}
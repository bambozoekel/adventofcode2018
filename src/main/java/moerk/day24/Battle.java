package moerk.day24;

import java.util.Comparator;

/**
 * @author matthias
 */
public class Battle {
	public Party fight( Party immuneSystem, Party infection ) {
		FightAssembler assembler = new FightAssembler( immuneSystem, infection );

		while ( immuneSystem.hasUnitsLeft() && infection.hasUnitsLeft() ) {
			if ( !assembler.assemble()
				.stream()
				.sorted( Comparator.comparing( Fight::getAttackerInitiative ).reversed() )
				.map( Fight::fight )
				.reduce( false, Boolean::logicalOr ) ) {
				break;
			}
		}

		return immuneSystem.getUnits() > 0 ? immuneSystem : infection;
	}
}
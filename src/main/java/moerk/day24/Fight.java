package moerk.day24;

/**
 * @author matthias
 */
public class Fight {
	private final Group attacker;
	private final Group defender;

	public Fight( Group attacker, Group defender ) {
		this.attacker = attacker;
		this.defender = defender;
	}

	public int getAttackerInitiative() {
		return attacker.getInitiative();
	}

	public Group getDefender() {
		return defender;
	}

	public void fight() {
		int defenderUnits = defender.getUnits();
		defender.takeAttack( attacker.getDamage( defender ) );
		System.out.println( attacker.getParty().getName() + " " + attacker.getIndex() + " attacks " + defender.getParty().getName() + " " + defender.getIndex() + ": " + defenderUnits + " -> " + defender.getUnits() );
	}
}
package moerk.day24;

import java.util.Set;

/**
 * @author matthias
 */
public class GroupConfiguration {
	private Party party;
	private int hitPointsPerUnit;
	private int attackDamage;
	private int initiative;
	private Set<AttackType> immunities;
	private Set<AttackType> weaknesses;
	private AttackType attackType;
	private int units;

	public Party getParty() {
		return party;
	}

	public void setParty( Party party ) {
		this.party = party;
	}

	public int getHitPointsPerUnit() {
		return hitPointsPerUnit;
	}

	public void setHitPointsPerUnit( int hitPointsPerUnit ) {
		this.hitPointsPerUnit = hitPointsPerUnit;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage( int attackDamage ) {
		this.attackDamage = attackDamage;
	}

	public int getInitiative() {
		return initiative;
	}

	public void setInitiative( int initiative ) {
		this.initiative = initiative;
	}

	public Set<AttackType> getImmunities() {
		return immunities;
	}

	public void setImmunities( Set<AttackType> immunities ) {
		this.immunities = immunities;
	}

	public Set<AttackType> getWeaknesses() {
		return weaknesses;
	}

	public void setWeaknesses( Set<AttackType> weaknesses ) {
		this.weaknesses = weaknesses;
	}

	public AttackType getAttackType() {
		return attackType;
	}

	public void setAttackType( AttackType attackType ) {
		this.attackType = attackType;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits( int units ) {
		this.units = units;
	}
}
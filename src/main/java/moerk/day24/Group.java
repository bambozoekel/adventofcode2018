package moerk.day24;

import com.google.common.base.MoreObjects;

import java.util.Set;

/**
 * @author matthias
 */
public class Group {
	private final Party party;
	private int index;
	private final int hitPointsPerUnit;
	private final int attackDamage;
	private final int initiative;
	private final Set<AttackType> immunities;
	private final Set<AttackType> weaknesses;
	private final AttackType attackType;
	private int units;
	private int boost;

	public Group( GroupConfiguration configuration ) {
		party = configuration.getParty();
		hitPointsPerUnit = configuration.getHitPointsPerUnit();
		attackDamage = configuration.getAttackDamage();
		initiative = configuration.getInitiative();
		immunities = configuration.getImmunities();
		weaknesses = configuration.getWeaknesses();
		attackType = configuration.getAttackType();
		units = configuration.getUnits();
	}

	public Party getParty() {
		return party;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex( int index ) {
		this.index = index;
	}

	public int getInitiative() {
		return initiative;
	}

	public Set<AttackType> getImmunities() {
		return immunities;
	}

	public Set<AttackType> getWeaknesses() {
		return weaknesses;
	}

	public int getUnits() {
		return units;
	}

	public boolean hasUnitsLeft() {
		return units > 0;
	}

	public void setBoost( int boost ) {
		this.boost = boost;
	}

	public int getEffectivePower() {
		return units * (attackDamage + boost);
	}

	public int getDamage( Group enemy ) {
		int factor = getAttackFactor( enemy );
		return factor * getEffectivePower();
	}

	private int getAttackFactor( Group enemy ) {
		if ( enemy.getWeaknesses().contains( attackType ) ) {
			return 2;
		}
		else if ( enemy.getImmunities().contains( attackType ) ) {
			return 0;
		}
		else {
			return 1;
		}
	}

	public void takeAttack( int damage ) {
		units = Math.max( 0, units - damage / hitPointsPerUnit );
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper( this )
			.add( "party", party.getName() )
			.add( "index", index )
			.add( "hitPointsPerUnit", hitPointsPerUnit )
			.add( "attackDamage", attackDamage )
			.add( "initiative", initiative )
			.add( "immunities", immunities )
			.add( "weaknesses", weaknesses )
			.add( "attackType", attackType )
			.add( "units", units )
			.toString();
	}
}
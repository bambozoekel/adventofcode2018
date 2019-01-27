package moerk.day24;

import com.google.common.base.Splitter;
import moerk.Util;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author matthias
 */
public class PartyReader {
	private static final String RESOURCE = "day24/groups.txt";

	private static final String ATTACK_TYPES = "radiation|cold|fire|bludgeoning|slashing";
	private static final Pattern PATTERN = Pattern.compile( "(\\d+) units each with (\\d+) hit points(?: \\((immune|weak) to ((?:" + ATTACK_TYPES + ")(?:, (?:" + ATTACK_TYPES + "))*)(?:; (immune|weak) to ((?:" + ATTACK_TYPES + ")(?:, (?:" + ATTACK_TYPES + "))*))?\\))? with an attack that does (\\d+) (" + ATTACK_TYPES + ") damage at initiative (\\d+)" );
	private static final Splitter SPLITTER = Splitter.on(",").trimResults();

	public Party readImmuneSystem() {
		return readParty( "Immune System:" );
	}

	public Party readInfection() {
		return readParty( "Infection:" );
	}

	private Party readParty( String name ) {
		List<String> lines = Util.lines( RESOURCE );

		int i = 0;
		while ( !lines.get( i ).equals( name ) ) {
			i++;
		}

		Party party = new Party( name.substring( 0, name.length() - 1 ) );

		i++;
		Matcher matcher;
		while ( i < lines.size() && (matcher = PATTERN.matcher( lines.get( i ))).matches() ) {
			party.addGroup( createGroup( matcher, party ) );
			i++;
		}

		return party;
	}

	private Group createGroup( Matcher matcher, Party party ) {
		GroupConfiguration configuration = new GroupConfiguration();
		configuration.setParty( party );
		configuration.setUnits( Integer.parseInt( matcher.group( 1 ) ) );
		configuration.setHitPointsPerUnit( Integer.parseInt( matcher.group( 2 ) ) );
		configuration.setImmunities( getImmunities( matcher ) );
		configuration.setWeaknesses( getWeaknesses( matcher ) );
		configuration.setAttackDamage( Integer.parseInt( matcher.group( 7 ) ) );
		configuration.setAttackType( getAttackType( matcher.group( 8 ) ) );
		configuration.setInitiative( Integer.parseInt( matcher.group( 9 ) ) );

		Group group = new Group( configuration );
		group.setIndex( party.getGroups().size() + 1 );
		return group;
	}

	private Set<AttackType> getImmunities( Matcher matcher ) {
		return getWeaknessesOrimmunities( matcher, "immune" );
	}

	private Set<AttackType> getWeaknesses( Matcher matcher ) {
		return getWeaknessesOrimmunities( matcher, "weak" );
	}

	private Set<AttackType> getWeaknessesOrimmunities( Matcher matcher, String type ) {
		if ( type.equals( matcher.group( 3 ) ) ) {
			return getAttackTypes( SPLITTER.split( matcher.group( 4 ) ) );
		}
		else if ( type.equals( matcher.group( 5 ) ) ) {
			return getAttackTypes( SPLITTER.split( matcher.group( 6 ) ) );
		}
		else {
			return Collections.emptySet();
		}
	}

	private Set<AttackType> getAttackTypes( Iterable<String> types ) {
		Set<AttackType> attackTypes = new HashSet<>();
		types.forEach( type -> attackTypes.add( getAttackType( type ) ) );
		return attackTypes;
	}

	private AttackType getAttackType( String value ) {
		return AttackType.valueOf( value.toUpperCase() );
	}
}
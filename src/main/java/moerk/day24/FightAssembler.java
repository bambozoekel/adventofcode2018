package moerk.day24;

import com.google.common.collect.Streams;
import moerk.day24.Fight;
import moerk.day24.Group;
import moerk.day24.Party;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author matthias
 */
public class FightAssembler {
	private final Party immuneSystem;
	private final Party infection;
	private List<Fight> fights;

	public FightAssembler( Party immuneSystem, Party infection ) {
		this.immuneSystem = immuneSystem;
		this.infection = infection;
	}

	public Collection<Fight> assemble() {
		fights = new ArrayList<>();

		groupsInSelectingOrder()
			.peek( g -> System.out.println( "Selecting: " + g.getParty().getName() + " group " + g.getIndex() + ": " + g.getEffectivePower() + " " + g.getInitiative() ) )
			.map( this::pickFight )
			.filter( Optional::isPresent )
			.map( Optional::get )
			.peek( f -> System.out.println( "Selected enemy: " + f.getDefender().getParty().getName() + " " + f.getDefender().getIndex() ) )
			.peek( f -> System.out.println( "---------------" ) )
			.forEach( fights::add );

		if ( fights.isEmpty() ) {
			throw new IllegalStateException();
		}

		return fights;
	}

	private Stream<Group> groupsInSelectingOrder() {
		return Streams.concat( immuneSystem.getGroups().stream(), infection.getGroups().stream() )
			.filter( Group::hasUnitsLeft )
			.sorted( Comparator.comparing( Group::getEffectivePower ).reversed().thenComparing( Comparator.comparing( Group::getInitiative ).reversed() ) );
	}

	private Optional<Fight> pickFight( Group group ) {
		return getEnemies( group )
			.stream()
			.peek( e -> System.out.println( "Possible enemy: " + e.getParty().getName() + " " + e.getIndex() + ": " + group.getDamage( e ) + " " + e.getEffectivePower() + " " + e.getInitiative() ) )
			.max( Comparator.comparing( group::getDamage )
				.thenComparing( Group::getEffectivePower )
				.thenComparing( Group::getInitiative ) )
			.map( enemy -> new Fight( group, enemy ) );
	}

	private Set<Group> getEnemies( Group group ) {
		Party enemy;
		if ( group.getParty() == immuneSystem ) {
			enemy = infection;
		}
		else {
			enemy = immuneSystem;
		}

		return enemy.getGroups()
			.stream()
			.filter( this::isNotPickedForFight )
			.filter( Group::hasUnitsLeft )
			.filter( e -> group.getDamage( e ) > 0 )
			.collect( Collectors.toSet() );
	}

	private boolean isNotPickedForFight( Group group ) {
		return !fights.stream()
			.map( Fight::getDefender )
			.collect( Collectors.toSet() )
			.contains( group );
	}
}
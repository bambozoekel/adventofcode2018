package moerk.day24;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author matthias
 */
public class Party {
	private final String name;
	private final Collection<Group> groups;

	public Party( String name ) {
		this.name = name;
		this.groups = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void addGroup( Group group ) {
		groups.add( group );
	}

	public Collection<Group> getGroups() {
		return groups;
	}

	public boolean hasUnitsLeft() {
		return getUnits() > 0;
	}

	public int getUnits() {
		return groups.stream().mapToInt( Group::getUnits ).sum();
	}

	public void setBoost( int boost ) {
		groups.forEach( g -> g.setBoost( boost ) );
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper( this )
			.add( "name", name )
			.add( "groups", groups )
			.toString();
	}
}
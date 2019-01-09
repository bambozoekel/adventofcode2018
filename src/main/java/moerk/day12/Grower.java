package moerk.day12;

import java.util.Map;

/**
 * @author matthias
 */
public class Grower {
	private StringBuilder currentGeneration;
	private Map<String, String> growRules;

	public Grower( Situation situation ) {
		currentGeneration = new StringBuilder( ".........." );
		currentGeneration.append( situation.getInitalState() );
		currentGeneration.append( "............................................................................................................................................................................................................................................................................................................................................................................" );

		growRules = situation.getRules();
	}

	public void grow( int generations ) {
		for ( int generation = 1; generation <= generations; generation++ ) {
			StringBuilder nextGeneration = new StringBuilder(currentGeneration.length());
			nextGeneration.append( ".." );
			for ( int i = 0; i < currentGeneration.length() - 4; i++ ) {
				String s = currentGeneration.substring( i, i+ 5 );
				String newPlant = growRules.get( s );
				nextGeneration.append( newPlant );
			}
			nextGeneration.append( ".." );
			currentGeneration = nextGeneration;
		}
	}

	public long getSum() {
		return getSum( 0 );
	}

	public long getSum( long offset ) {
		long sum = 0;
		for ( int i = 0; i < currentGeneration.length(); i++ ) {
			if ( currentGeneration.charAt( i ) == '#' ) {
				sum += (i - 10) + offset;
			}
		}

		return sum;
	}
}
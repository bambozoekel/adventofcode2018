package moerk.day18.puzzle1;

import moerk.day18.Area;
import moerk.day18.AreaReader;
import moerk.day18.Type;

import java.util.HashMap;
import java.util.Map;

/**
 * @author matthias219919
 */
public class AreaChangeWatcher {
	public static void main( String[] args ) {
		System.out.println( (1000000000 - 593) % 28 );

		Map<Long, Integer> values = new HashMap<>();
		Area area = new AreaReader().read();
//		int minutes = 1000000000;
		int minutes = 608;
		for ( int i = 0; i < minutes; i++ ) {
			changeArea( area );
			area.commit();

			long value = area.getTreeCount() * area.getLumberYardCount();
			Integer previousMinute = values.put( value, i );
			if ( previousMinute != null ) {
				System.out.println( "Minute: " + i + " " + value + " " + previousMinute + " " + (i - previousMinute));
			}
		}
			area.print();
		System.out.println( area.getTreeCount() );
		System.out.println( area.getLumberYardCount() );
		System.out.println( area.getTreeCount() * area.getLumberYardCount() );
	}

	private static void changeArea( Area area ) {
		for ( int x = 0; x < area.getWidth(); x++ ) {
			for ( int y = 0; y < area.getHeight(); y++ ) {
				changeArea( area, x, y );
			}
		}
	}

	private static void changeArea( Area area, int x, int y ) {
		Type type = area.get( x, y );

		if ( type == Type.OPEN ) {
			changeOpen( area, x, y );
		}
		else if ( type == Type.TREES ) {
			changeTrees( area, x, y );

		}
		else if ( type == Type.LUMBERYARD ) {
			changeLumberYard( area, x, y );
		}
	}

	private static void changeOpen( Area area, int x, int y ) {
		if ( area.getNeighbouringTypeCount( x, y ).get( Type.TREES ) >=3 ) {
			area.set( x, y, Type.TREES );
		}
		else {
			area.set( x, y, Type.OPEN );
		}
	}

	private static void changeTrees( Area area, int x, int y ) {
		if ( area.getNeighbouringTypeCount( x, y ).get( Type.LUMBERYARD ) >=3 ) {
			area.set( x, y, Type.LUMBERYARD );
		}
		else {
			area.set( x, y, Type.TREES );
		}
	}

	private static void changeLumberYard( Area area, int x, int y ) {
		Map<Type, Integer> counts = area.getNeighbouringTypeCount( x, y );
		if ( counts.get( Type.LUMBERYARD ) >= 1 && counts.get( Type.TREES ) >= 1 ) {
			area.set( x, y, Type.LUMBERYARD );
		}
		else {
			area.set( x, y, Type.OPEN );
		}
	}
}
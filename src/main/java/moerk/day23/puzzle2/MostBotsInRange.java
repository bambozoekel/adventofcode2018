package moerk.day23.puzzle2;

import moerk.day23.Cube;
import moerk.day23.NanoBot;
import moerk.day23.NanoBotReader;
import moerk.day23.Point;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @author matthias
 */
public class MostBotsInRange {
	public static void main( String[] args ) {
		Point origin = new Point( 0 , 0, 0 );
		Collection<NanoBot> bots = new NanoBotReader().read();

		Cube cube = getFirstCube( bots );
		Set<Cube> bestSubCubes = new HashSet<>();
		while ( cube.getSize() > 0 ) {
			bestSubCubes.clear();
			long maxBotsInRange = 0;
			for ( Cube subCube : cube.subCubify() ) {
				long botsInRange = inRange( bots, subCube );
				if ( botsInRange > maxBotsInRange ) {
					maxBotsInRange = botsInRange;
					bestSubCubes.clear();
					bestSubCubes.add( subCube );
				}
				else if ( botsInRange == maxBotsInRange ) {
					bestSubCubes.add( subCube );
				}
			}

			cube = bestSubCubes.stream().min( Comparator.comparingInt( c -> origin.getDistance( c.getOrigin() ) ) ).orElseThrow();
		}

		System.out.println( cube.getOrigin().getDistance( origin ) );
	}

	private static Cube getFirstCube( Collection<NanoBot> bots ) {
		int minX = Integer.MAX_VALUE;
		int maxX = 0;
		int minY = Integer.MAX_VALUE;
		int maxY = 0;
		int minZ = Integer.MAX_VALUE;
		int maxZ = 0;

		for ( NanoBot bot : bots ) {
			minX = Math.min( minX, bot.getLocation().getX() );
			maxX = Math.max( maxX, bot.getLocation().getX() );
			minY = Math.min( minY, bot.getLocation().getY() );
			maxY = Math.max( maxY, bot.getLocation().getY() );
			minZ = Math.min( minZ, bot.getLocation().getZ() );
			maxZ = Math.max( maxZ, bot.getLocation().getZ() );
		}

		int side = Math.max( maxX - minX, Math.max( maxY - minY, maxZ - minZ ) );

		int i = 1;
		while ( i < side ) {
			i *= 2;
		}

		return new Cube( new Point( minX, minY, minZ ), i );
	}

	private static long inRange( Collection<NanoBot> bots, Cube cube ) {
		return bots.stream()
			.filter( bot -> bot.intersects( cube ) )
			.count();
	}
}
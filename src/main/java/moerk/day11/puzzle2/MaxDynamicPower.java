package moerk.day11.puzzle2;

import moerk.day11.BlockPower;
import moerk.day11.PowerGrid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author matthias
 */
public class MaxDynamicPower {
	public static void main( String[] args ) {
		long start = System.currentTimeMillis();
		PowerGrid grid = new PowerGrid( 7165 );

//		BlockPower best = null;
//
//		for ( int blockSize = 1; blockSize <= 300; blockSize++ ) {
//			long start1 = System.currentTimeMillis();
//			BlockPower power = grid.calculateMaxBlockPowerLevel( blockSize );
//
//			if ( best == null || power.getPower() > best.getPower() ) {
//				best = power;
//			}
//
//			long stop1 = System.currentTimeMillis();
//			System.out.println( blockSize + ": " + (stop1 - start1) );
//		}

		List<Integer> blockSizes = new ArrayList<>( 300 );
		IntStream.range( 1, 300 ).forEach( blockSizes::add );
		BlockPower best = blockSizes.stream()
			.map( grid::calculateMaxBlockPowerLevel )
			.max( Comparator.comparing( BlockPower::getPower ) )
			.orElseThrow();

		long stop = System.currentTimeMillis();
		System.out.println(best.getPower());
		System.out.println( best.getX() + "," + best.getY() + "," + best.getBlockSize() );
		System.out.println( stop - start );
	}
}
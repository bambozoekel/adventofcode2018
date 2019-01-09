package moerk.day11.puzzle1;

import moerk.day11.BlockPower;
import moerk.day11.PowerGrid;

/**
 * @author matthias
 */
public class MaxFuel {
	public static void main( String[] args ) {
		long start = System.currentTimeMillis();
		PowerGrid grid = new PowerGrid( 7165 );

		BlockPower maxPower = grid.calculateMaxBlockPowerLevel( 3 );
		long stop = System.currentTimeMillis();
		System.out.println(maxPower.getPower());
		System.out.println( maxPower.getX() + "," + maxPower.getY() );
		System.out.println( stop - start );
	}
}
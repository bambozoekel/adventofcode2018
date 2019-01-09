package moerk.day11;

/**
 * @author matthias
 */
public class PowerGrid {
	private static final int SIZE = 300;

	private final int serialNumber;
	private final int[][] cells;

	public PowerGrid( int serialNumber ) {
		this.serialNumber = serialNumber;
		cells = new int[SIZE][SIZE];
		calculatePowerLevels();
	}

	private void calculatePowerLevels() {
		for ( int x = 1; x <= SIZE; x++ ) {
			for ( int y = 1; y <= SIZE; y++ ) {
				cells[x - 1][y - 1] = calculatePowerLevel( x, y );
			}
		}
	}

	private int calculatePowerLevel( int x, int y ) {
		int rackID = x + 10;
		int powerLevel = rackID * y;
		powerLevel += serialNumber;
		powerLevel *= rackID;
		return hundreds( powerLevel ) - 5;
	}

	private int hundreds( int powerLevel ) {
		if ( powerLevel < 100 ) {
			return 0;
		}
		else {
			String s = Integer.toString( powerLevel );
			return s.charAt( s.length() - 3 ) - 48;
		}
	}

	public BlockPower calculateMaxBlockPowerLevel( int blockSize ) {
		int maxPower = Integer.MIN_VALUE;
		int maxX = 0;
		int maxY = 0;

		long start = System.currentTimeMillis();
		for ( int x = 0; x <= 300 - blockSize; x++ ) {
			for ( int y = 0; y <= 300 - blockSize; y++ ) {
				int power = getBlockPowerLevel( x, y, blockSize );
				if ( power > maxPower ) {
					maxPower = power;
					maxX = x;
					maxY = y;
				}
			}
		}
		long stop = System.currentTimeMillis();
		System.out.println( blockSize + ": " + (stop - start) + " " + Math.pow((300 - blockSize) + 1, 2));

		return new BlockPower( maxX + 1, maxY + 1, blockSize, maxPower );
	}

	private int getBlockPowerLevel( int blockx, int blocky, int blockSize ) {
		int powerLevel = 0;
		for ( int x = blockx; x < blockx + blockSize; x++ ) {
			for ( int y = blocky; y < blocky + blockSize; y++ ) {
				powerLevel += cells[x][y];
			}
		}

		return powerLevel;
	}
}
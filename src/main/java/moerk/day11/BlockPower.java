package moerk.day11;

/**
 * @author matthias
 */
public class BlockPower {
	private final int x;
	private final int y;
	private final int blockSize;
	private final int power;

	public BlockPower( int x, int y, int blockSize, int power ) {
		this.x = x;
		this.y = y;
		this.blockSize = blockSize;
		this.power = power;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public int getPower() {
		return power;
	}
}
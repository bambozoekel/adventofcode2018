package moerk.day9;

/**
 * @author matthias
 */
public class Settings {
	private final int players;
	private final int marbles;

	public Settings( int players, int marbles ) {
		this.players = players;
		this.marbles = marbles;
	}

	public int getPlayers() {
		return players;
	}

	public int getMarbles() {
		return marbles;
	}
}
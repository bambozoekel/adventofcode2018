package moerk.day15;

/**
 * @author matthias
 */
public interface Entity {
	boolean passable();
	boolean isEnemy( Entity entity );
}
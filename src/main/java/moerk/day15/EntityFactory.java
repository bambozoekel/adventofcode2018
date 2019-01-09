package moerk.day15;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author matthias
 */
public class EntityFactory {
	private static final Map<String, Supplier<Entity>> ENTITY_SUPPLIERS = Map.of(
		"#", () -> Wall.INSTANCE,
		".", () -> EmptySpace.INSTANCE,
		"G", Goblin::new,
		"E", Elve::new
	);

	public Entity create( String str ) {
		return ENTITY_SUPPLIERS.get( str ).get();
	}
}
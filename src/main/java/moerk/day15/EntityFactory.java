package moerk.day15;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author matthias
 */
public class EntityFactory {
	private static final Map<String, Supplier<Entity>> DEFAULT_ENTITY_SUPPLIERS = Map.of(
		"#", () -> Wall.INSTANCE,
		".", () -> EmptySpace.INSTANCE,
		"G", Goblin::new,
		"E", Elve::new
	);

	private final Map<String, Supplier<Entity>> entitySuppliers;

	public EntityFactory() {
		entitySuppliers = DEFAULT_ENTITY_SUPPLIERS;
	}

	public EntityFactory( int elveAttack ) {
		entitySuppliers = new HashMap<>( DEFAULT_ENTITY_SUPPLIERS );
		entitySuppliers.put( "E", () -> new Elve( elveAttack ) );
	}

	public Entity create( String str ) {
		return entitySuppliers.get( str ).get();
	}
}
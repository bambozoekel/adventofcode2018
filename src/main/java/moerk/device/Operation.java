package moerk.device;

/**
 * @author matthias
 */
public interface Operation {
	int apply( Device device, int a, int b );
}
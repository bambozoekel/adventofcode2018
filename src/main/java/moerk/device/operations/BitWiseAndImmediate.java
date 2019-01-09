package moerk.device.operations;

import moerk.device.Device;
import moerk.device.Operation;

/**
 * @author matthias
 */
public class BitWiseAndImmediate implements Operation {
	@Override
	public int apply( Device device, int a, int b ) {
		return device.getValue( a ) & b;
	}
}
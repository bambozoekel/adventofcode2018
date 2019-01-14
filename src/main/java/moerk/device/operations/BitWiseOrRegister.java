package moerk.device.operations;

import moerk.device.Device;
import moerk.device.Operation;

/**
 * @author matthias
 */
public class BitWiseOrRegister implements Operation {
	@Override
	public int apply( Device device, int a, int b ) {
		return device.getValue( a ) | device.getValue( b );
	}

	@Override
	public String toString() {
		return "borr";
	}
}
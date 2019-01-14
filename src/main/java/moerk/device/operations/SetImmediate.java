package moerk.device.operations;

import moerk.device.Device;
import moerk.device.Operation;

/**
 * @author matthias
 */
public class SetImmediate implements Operation {
	@Override
	public int apply( Device device, int a, int b ) {
		return a;
	}

	@Override
	public String toString() {
		return "seti";
	}
}
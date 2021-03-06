package moerk.device.operations;

import moerk.device.Device;
import moerk.device.Operation;

/**
 * @author matthias
 */
public class EqualsImmediateRegister implements Operation {
	@Override
	public int apply( Device device, int a, int b ) {
		return a == device.getValue( b ) ? 1 : 0;
	}

	@Override
	public String toString() {
		return "eqir";
	}
}
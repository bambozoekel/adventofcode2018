package moerk.device;

import java.util.List;

/**
 * @author matthias
 */
public class Instruction {
	private final Operation operation;
	private final List<Integer> parameters;

	public Instruction( Operation operation, List<Integer> parameters ) {
		this.operation = operation;
		this.parameters = parameters;
	}

	public Operation getOperation() {
		return operation;
	}

	public List<Integer> getParameters() {
		return parameters;
	}
}
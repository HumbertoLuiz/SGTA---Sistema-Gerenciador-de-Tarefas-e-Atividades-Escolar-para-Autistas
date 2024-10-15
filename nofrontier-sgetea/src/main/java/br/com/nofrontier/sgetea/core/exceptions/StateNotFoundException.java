package br.com.nofrontier.sgetea.core.exceptions;

public class StateNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public StateNotFoundException(String message) {
		super(message);
	}
	
	public StateNotFoundException(Long stateId) {
		this(String.format("There is no state register with a code %d", stateId));
	}
	
}

package br.com.nofrontier.sgetea.core.exceptions;

public class GuardianNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public GuardianNotFoundException(String message) {
		super(message);
	}
	
	public GuardianNotFoundException(Long guardianId) {
		this(String.format("There is no guardian register with a code %d", guardianId));
	}
}

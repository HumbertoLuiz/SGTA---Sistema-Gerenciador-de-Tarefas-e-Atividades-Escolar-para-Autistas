package br.com.xfrontier.sgetea.core.exceptions;

public class InstitutionNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public InstitutionNotFoundException(String message) {
		super(message);
	}
	
	public InstitutionNotFoundException(Long institutionId) {
		this(String.format("There is no institution register with a code %d", institutionId));
	}

}

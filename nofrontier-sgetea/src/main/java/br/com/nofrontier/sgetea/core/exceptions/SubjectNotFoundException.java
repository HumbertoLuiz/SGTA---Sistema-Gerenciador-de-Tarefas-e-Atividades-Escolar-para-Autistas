package br.com.nofrontier.sgetea.core.exceptions;

public class SubjectNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public SubjectNotFoundException(String message) {
		super(message);
	}
	
	public SubjectNotFoundException(Long disciplineId) {
		this(String.format("There is no discipline register with a code %d", disciplineId));
	}

}

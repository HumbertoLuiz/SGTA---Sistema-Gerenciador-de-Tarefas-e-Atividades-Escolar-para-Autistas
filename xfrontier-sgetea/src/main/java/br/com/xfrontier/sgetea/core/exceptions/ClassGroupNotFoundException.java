package br.com.xfrontier.sgetea.core.exceptions;

public class ClassGroupNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public ClassGroupNotFoundException(String message) {
		super(message);
	}
	
	public ClassGroupNotFoundException(Long courseClassId) {
		this(String.format("There is no class register with a code %d", courseClassId));
	}

}

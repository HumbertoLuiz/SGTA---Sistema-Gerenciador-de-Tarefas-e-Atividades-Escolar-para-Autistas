package br.com.xfrontier.sgetea.core.exceptions;

public class StudentNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public StudentNotFoundException(String message) {
		super(message);
	}
	
	public StudentNotFoundException(Long studentId) {
		this(String.format("There is no student register with a code %d", studentId));
	}

}

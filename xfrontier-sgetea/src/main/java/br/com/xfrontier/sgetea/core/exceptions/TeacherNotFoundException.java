package br.com.xfrontier.sgetea.core.exceptions;

public class TeacherNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public TeacherNotFoundException(String message) {
		super(message);
	}
	
	public TeacherNotFoundException(Long teacherId) {
		this(String.format("There is no teacher register with a code %d", teacherId));
	}

}

package br.com.nofrontier.sgetea.core.exceptions;

public class CourseNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public CourseNotFoundException(String message) {
		super(message);
	}
	
	public CourseNotFoundException(Long courseId) {
		this(String.format("There is no course register with a code %d", courseId));
	}

}

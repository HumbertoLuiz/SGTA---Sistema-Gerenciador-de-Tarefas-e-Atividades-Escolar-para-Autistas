package br.com.xfrontier.sgetea.core.exceptions;

public class TaskNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public TaskNotFoundException(String message) {
		super(message);
	}
	
	public TaskNotFoundException(Long taskId) {
		this(String.format("There is no task register with a code %d", taskId));
	}

}

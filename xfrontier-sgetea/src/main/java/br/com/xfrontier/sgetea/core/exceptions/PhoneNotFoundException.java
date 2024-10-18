package br.com.xfrontier.sgetea.core.exceptions;

public class PhoneNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public PhoneNotFoundException(String message) {
		super(message);
	}
	
	public PhoneNotFoundException(Long phoneId) {
		this(String.format("There is no phone register with a code %d", phoneId));
	}
	
}

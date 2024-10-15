package br.com.nofrontier.sgetea.core.exceptions;

public class UserProfileNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public UserProfileNotFoundException(String message) {
		super(message);
	}
	
	public UserProfileNotFoundException(Long userProfileId) {
		this(String.format("There is no userProfile register with a code %d", userProfileId));
	}

}

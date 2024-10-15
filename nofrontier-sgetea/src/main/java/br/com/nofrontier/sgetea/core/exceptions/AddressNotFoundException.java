package br.com.nofrontier.sgetea.core.exceptions;

public class AddressNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public AddressNotFoundException(String message) {
		super(message);
	}
	
	public AddressNotFoundException(Long addressId) {
		this(String.format("There is no address register with a code %d", addressId));
	}
	
}

package br.com.nofrontier.sgetea.core.exceptions;

public class CountryNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public CountryNotFoundException(String message) {
		super(message);
	}

	public CountryNotFoundException(Long countryId) {
		this(String.format("There is no country register with a code %d", countryId));
	}

}

package br.com.nofrontier.sgetea.core.exceptions;

public class AdministrativeStaffNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public AdministrativeStaffNotFoundException(String message) {
		super(message);
	}
	
	public AdministrativeStaffNotFoundException(Long employeeId) {
		this(String.format("There is no employee register with a code %d", employeeId));
	}
}

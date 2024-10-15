package br.com.nofrontier.sgetea.core.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.nofrontier.sgetea.core.exceptions.UserAlreadyRegisteredException;
import br.com.nofrontier.sgetea.core.models.User;
import br.com.nofrontier.sgetea.core.repository.UserRepository;

@Component
public class UserValidator {

	@Autowired
	private UserRepository userRepository;

	public void validate(User user) {
		validateEmail(user);
		validateCpf(user);
	}

	private void validateEmail(User user) {
		if (userRepository.isEmailAlreadyRegistered(user)) {
			var message = "User Registered Already exists with this e-mail";
			var fieldError = new FieldError(user.getClass().getName(), "email", user.getEmail(), false, null, null,
					message);
			throw new UserAlreadyRegisteredException(message, fieldError);
		}
		validateCpf(user);
	}

	private void validateCpf(User user) {
		if (userRepository.isCpfAlreadyRegistered(user)) {
			var message = "There is already a user registered with this cpf";
			var fieldError = new FieldError(user.getClass().getName(), "cpf", user.getClass().getName(), false, null,
					null, message);
			throw new UserAlreadyRegisteredException(message, fieldError);
		}
		// validatePaymentMethod(user);
	}

	/*
	 * private void validateKeyPix(User user) { if
	 * (userRepository.isKeyPixAlreadyRegistered(user)) { var message =
	 * "There is already a user registered with this key pix"; var fieldError = new
	 * FieldError(user.getClass().getName(), "keyPix", user.getKeyPix(), false,
	 * null, null, message); throw new UserAlreadyRegisteredException(message,
	 * fieldError); } if (user.isGuest() && user.getKeyPix() == null) { var message
	 * = "User type Housekeeper must be have a pix key"; var fieldError = new
	 * FieldError(user.getClass().getName(), "keyPix", user.getKeyPix(), false,
	 * null, null, message); throw new ValidatingException(message, fieldError); } }
	 */

	/*
	 * private void validatePaymentMethod(User user) { if
	 * (userRepository.isPaymentMethodAlreadyRegistered(user)) { var message =
	 * "There is already a PaymentMethod registered with this payment method"; var
	 * fieldError = new FieldError(user.getClass().getName(), "paymentMethod",
	 * user.getPaymentMethod(), false, null, null, message);
	 * 
	 * throw new ReserveAlreadyRegisteredException(message, fieldError); }
	 * 
	 * if (user.isGuest() && user.getPaymentMethod() == null) { var message =
	 * "User type Guest must be have a payment method"; var fieldError = new
	 * FieldError(user.getClass().getName(), "paymentMethod",
	 * user.getPaymentMethod(), false, null, null, message);
	 * 
	 * throw new ValidatingException(message, fieldError); } }
	 */
}

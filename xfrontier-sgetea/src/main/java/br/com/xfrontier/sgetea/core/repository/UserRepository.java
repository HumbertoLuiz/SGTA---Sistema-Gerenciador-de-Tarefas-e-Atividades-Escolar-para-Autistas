package br.com.xfrontier.sgetea.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.xfrontier.sgetea.core.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByCpf(String cpf);

	// Optional<User> findByKeyPix(String keyPix);

	// Optional<User> findByPaymentMethod(PaymentMethod paymentMethod);

	User findUserById(Long id);

	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from User u where u.id = :id")
	void removeUser(Long id);

	default Boolean isEmailAlreadyRegistered(User user) {
		if (user.getEmail() == null) {
			return false;
		}
		return findByEmail(user.getEmail()).map(userFound -> !userFound.getId().equals(user.getId())).orElse(false);
	}

	default Boolean isCpfAlreadyRegistered(User user) {
		if (user.getCpf() == null) {
			return false;
		}
		return findByCpf(user.getCpf()).map(userFound -> !userFound.getId().equals(user.getId()))
				.orElse(false);
	}

	/*
	 * default Boolean isKeyPixAlreadyRegistered(User user) { if (user.getKeyPix()
	 * == null) { return false; } return findByKeyPix(user.getKeyPix())
	 * .map(userFound -> !userFound.getId().equals(user.getId())) .orElse(false); }
	 */

	/*
	 * default Boolean isPaymentMethodAlreadyRegistered(User user) { if
	 * (user.getPaymentMethod() == null) { return false; } return
	 * findByPaymentMethod(user.getPaymentMethod()) .map(reserveFound ->
	 * !reserveFound.getId().equals(reserve.getId())).orElse(false); }
	 */

	boolean existsByEmail(String email);

	List<User> findByFirstNameIgnoreCaseContaining(String query);

	List<User> findByEmailIgnoreCaseContaining(String query);

	User findByEmailIgnoreCase(String email);
}

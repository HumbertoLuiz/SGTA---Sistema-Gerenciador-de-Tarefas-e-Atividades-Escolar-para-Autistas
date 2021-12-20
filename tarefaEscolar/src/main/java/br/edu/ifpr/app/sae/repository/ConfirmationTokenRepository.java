package br.edu.ifpr.app.sae.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.app.sae.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}

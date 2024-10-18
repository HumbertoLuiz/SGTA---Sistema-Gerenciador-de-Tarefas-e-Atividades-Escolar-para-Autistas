package br.com.xfrontier.sgetea.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xfrontier.sgetea.core.models.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
	
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}

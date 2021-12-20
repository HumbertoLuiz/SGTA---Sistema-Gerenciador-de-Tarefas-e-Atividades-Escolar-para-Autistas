package br.edu.ifpr.app.sae.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpr.app.sae.model.Servidor;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor, Long> {

	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from Servidor s where s.id = :id")
	void removeServidor(Long id);
	
}

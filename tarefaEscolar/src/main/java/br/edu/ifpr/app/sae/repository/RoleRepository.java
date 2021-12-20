package br.edu.ifpr.app.sae.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifpr.app.sae.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from Role r where r.id = :id")
	void removeRole(Long id);

	List<Role> findByNomeIgnoreCaseContaining(String nome);
	
	Role findByNome(@Param("nome") String nome);


}


package br.edu.ifpr.sgta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import br.edu.ifpr.sgta.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	List<Role> findByNome(String nome);
	
	/*@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from Role r where r.id = :id")
	void removeRole(Long id);*/

	List<Role> findByNomeIgnoreCaseContaining(String nome);


}


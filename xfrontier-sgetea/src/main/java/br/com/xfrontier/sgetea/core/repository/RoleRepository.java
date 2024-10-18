package br.com.xfrontier.sgetea.core.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.xfrontier.sgetea.core.models.Role;
import jakarta.transaction.Transactional;

public interface RoleRepository extends JpaRepository<Role, Long> {

	static Set<Role> findByName(String name) {
		return null;
	}

	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from Role r where r.id = :id")
	void removeRole(Long id);

	List<Role> findByNameIgnoreCaseContaining(String name);

	Set<Role> getRoleByName(String name);

}

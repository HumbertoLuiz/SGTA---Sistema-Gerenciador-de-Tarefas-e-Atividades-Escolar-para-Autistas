package br.edu.ifpr.app.sae.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpr.app.sae.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByEmailIgnoreCase(String email);
	
    Usuario findByEmail(String email);	
	
	Usuario findUsuarioById(Long id);
	
    List<Usuario> findByEmailIgnoreCaseContaining(String email);
    
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from Usuario u where u.id = :id")
	void removeUsuario(Long id);
	
	 
}
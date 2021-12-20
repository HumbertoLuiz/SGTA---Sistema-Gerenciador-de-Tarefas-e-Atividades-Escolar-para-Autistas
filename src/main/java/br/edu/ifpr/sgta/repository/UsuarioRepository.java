package br.edu.ifpr.sgta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifpr.sgta.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);	
	
	Usuario findUsuarioById(Long id);
	
    List<Usuario> findByEmailIgnoreCaseContaining(String email);
    
   	
	/*@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from Usuario u where u.id = :id")
	void removerUsuario(Long id);*/
}

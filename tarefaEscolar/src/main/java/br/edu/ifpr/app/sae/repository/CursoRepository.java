package br.edu.ifpr.app.sae.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifpr.app.sae.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
	
	List<Curso> findByDescricaoIgnoreCaseContaining(String query);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from Curso c where c.id = :id")
	void removerCurso(Long id);

}

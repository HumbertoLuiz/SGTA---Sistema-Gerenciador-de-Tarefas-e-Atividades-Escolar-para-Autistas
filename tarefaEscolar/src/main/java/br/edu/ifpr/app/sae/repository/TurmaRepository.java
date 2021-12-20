package br.edu.ifpr.app.sae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpr.app.sae.model.Aluno;
import br.edu.ifpr.app.sae.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
	
	@Query("select t from turma t join t.curso c where c.id = ?1")
	List<Turma> findByCurso(Long id);
	
	List<Turma> findByDescricaoIgnoreCaseContaining(String descricao);
			
	List<Aluno> findByAlunosTurma(String turma);
	
}

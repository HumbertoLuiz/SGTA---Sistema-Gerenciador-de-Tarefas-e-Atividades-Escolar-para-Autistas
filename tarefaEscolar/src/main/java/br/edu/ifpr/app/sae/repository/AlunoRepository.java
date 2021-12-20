package br.edu.ifpr.app.sae.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpr.app.sae.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	public List<Aluno> findByTurmaId(Long id_turma);
	
	List<Aluno> findByTurma(String turma);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from aluno a where a.id = :id")
	void removerAluno(Long id);
}

package br.edu.ifpr.app.sae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpr.app.sae.model.Disciplina;


@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>{
	
	@Query(value="SELECT * FROM Disciplina WHERE turma_id=:turmaid", nativeQuery=true)
	List<Disciplina> getDisciplinasByTurmaId(Long turmaid);

	public List<Disciplina> findByTurmaId(Long id);
	
	Disciplina findDisciplinaById(Long id_disciplina);
	

}

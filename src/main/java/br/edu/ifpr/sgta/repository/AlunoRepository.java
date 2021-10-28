package br.edu.ifpr.sgta.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpr.sgta.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{


}

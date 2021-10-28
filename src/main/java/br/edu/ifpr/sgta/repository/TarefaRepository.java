package br.edu.ifpr.sgta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpr.sgta.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}

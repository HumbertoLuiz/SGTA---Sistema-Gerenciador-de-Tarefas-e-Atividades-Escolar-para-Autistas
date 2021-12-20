package br.edu.ifpr.sgta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.sgta.model.Curso;


public interface CursoRepository extends JpaRepository<Curso, Long>{

}

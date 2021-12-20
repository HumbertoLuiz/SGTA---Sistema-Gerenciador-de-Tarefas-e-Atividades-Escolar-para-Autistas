package br.edu.ifpr.sgta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.sgta.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}

package br.edu.ifpr.app.sae.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.app.sae.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}

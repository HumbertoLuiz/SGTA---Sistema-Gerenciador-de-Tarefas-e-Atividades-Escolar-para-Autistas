package br.edu.ifpr.app.sae.service;

import java.util.List;

import br.edu.ifpr.app.sae.model.Disciplina;


public interface DisciplinaService {
	
	List<Disciplina> buscarTodos();
	
	List<Disciplina>  pesquisarPorCodigoTurma(Long id);
	
}

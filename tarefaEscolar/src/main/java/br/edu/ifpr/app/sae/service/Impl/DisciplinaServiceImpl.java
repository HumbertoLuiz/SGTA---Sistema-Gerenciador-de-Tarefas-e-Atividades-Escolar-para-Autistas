package br.edu.ifpr.app.sae.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpr.app.sae.model.Disciplina;
import br.edu.ifpr.app.sae.repository.DisciplinaRepository;
import br.edu.ifpr.app.sae.service.DisciplinaService;

@Service
public class DisciplinaServiceImpl implements DisciplinaService{
	
	@Autowired
	private DisciplinaRepository daoDisc;
	
	@Transactional(readOnly = true)
	@Override
	public List<Disciplina> buscarTodos() {
		
		return daoDisc.findAll();
	}

	@Override
	public List<Disciplina> pesquisarPorCodigoTurma(Long id) {
	
		return daoDisc.findByTurmaId(id);
	}

	
	
}

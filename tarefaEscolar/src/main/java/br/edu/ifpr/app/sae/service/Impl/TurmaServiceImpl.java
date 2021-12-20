package br.edu.ifpr.app.sae.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpr.app.sae.model.Disciplina;
import br.edu.ifpr.app.sae.model.Professor;
import br.edu.ifpr.app.sae.model.Turma;
import br.edu.ifpr.app.sae.repository.TurmaRepository;
import br.edu.ifpr.app.sae.service.TurmaService;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;     

    @Override
    public Turma createTurma() {
        return new Turma();
    }

    @Override
    public Turma saveTurma(Turma turma) {
        return turmaRepository.save(turma);
    }
    
    @Override
    public void addRow(Turma turma) {
        turma.getDisciplinaList().add(new Disciplina());
        turma.getProfessorList().add(new Professor());
    }    

    @Override
    public void removeDisciplina(Turma turma, Integer disciplinaIndex) {
        turma.getDisciplinaList().remove(disciplinaIndex.intValue());
    }

	@Override
	public List<Turma> findAllTurmas() {
		
		return turmaRepository.findAll();
	}
	


	
}

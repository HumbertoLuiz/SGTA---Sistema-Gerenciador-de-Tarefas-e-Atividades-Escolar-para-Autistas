package br.edu.ifpr.app.sae.service;

import java.util.List;

import br.edu.ifpr.app.sae.model.Turma;

public interface TurmaService {
	
    Turma createTurma();
    Turma saveTurma(Turma turma);
    void addRow(Turma turma);
    void removeDisciplina(Turma turma, Integer disciplinaIndex);
    List<Turma> findAllTurmas();

}

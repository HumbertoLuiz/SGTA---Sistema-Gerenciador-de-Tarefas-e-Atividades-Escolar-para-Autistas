package br.edu.ifpr.app.sae.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity(name = "tarefa")
public class Tarefa implements Serializable{

private static final long serialVersionUID = 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tarefa")
	private Long id;
		
	private String descricao;	
	
	private String local;	
	
	private String dtCriacaoTarefa;	
	
	private String dtFinalizacaoTarefa;
		
	@Enumerated(EnumType.STRING)
	private StatusTarefa statusTarefa;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_disciplina")
	private Disciplina disciplina;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_turma")
	private Turma turma;
	
	public static String convertData() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDtCriacaoTarefa() {
		return dtCriacaoTarefa;
	}

	public void setDtCriacaoTarefa(String dtCriacaoTarefa) {
		this.dtCriacaoTarefa = dtCriacaoTarefa;
	}

	public String getDtFinalizacaoTarefa() {
		return dtFinalizacaoTarefa;
	}

	public void setDtFinalizacaoTarefa(String dtFinalizacaoTarefa) {
		this.dtFinalizacaoTarefa = dtFinalizacaoTarefa;
	}

	public StatusTarefa getStatusTarefa() {
		return statusTarefa;
	}

	public void setStatusTarefa(StatusTarefa statusTarefa) {
		this.statusTarefa = statusTarefa;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	
}

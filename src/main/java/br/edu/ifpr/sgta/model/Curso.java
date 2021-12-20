package br.edu.ifpr.sgta.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name = "TB_CURSO")
public class Curso implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="Campo curso obrigatorio")
	@Column(nullable = false)
	private String nome;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "TB_CURSO_TURMA",
	   joinColumns =  @JoinColumn(name = "ID_CURSO", foreignKey = @ForeignKey(name = "FK_CURSO_TURMA")),
	   inverseJoinColumns =  @JoinColumn(name = "ID_TURMA", foreignKey = @ForeignKey(name = "FK_TURMA_CURSO")))
	private Turma turma;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Curso(@NotBlank(message = "Campo curso obrigatorio") String nome) {
		super();
		this.nome = nome;
	}

	public Curso() {
		super();
		
	}

	

	
	
	
	
}

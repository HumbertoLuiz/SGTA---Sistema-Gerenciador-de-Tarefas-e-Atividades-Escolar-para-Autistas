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

@Entity(name = "TB_DISCIPLINA")
public class Disciplina implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="Campo descricao obrigatorio")
	@Column(nullable = false)
	private String descricao;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "TB_DISC_ALUNO",
	   joinColumns =  @JoinColumn(name = "DISC_ID", foreignKey = @ForeignKey(name = "FK_DISC_ALUNO")),
	   inverseJoinColumns =  @JoinColumn(name = "ALUNO_ID", foreignKey = @ForeignKey(name = "FK_ALUNO_DISC")))
	private Aluno aluno;


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
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
}

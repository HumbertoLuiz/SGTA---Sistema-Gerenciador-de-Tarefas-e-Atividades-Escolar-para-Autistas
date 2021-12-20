package br.edu.ifpr.app.sae.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity(name = "aluno")
//@PrimaryKeyJoinColumn(name="id_aluno")
public class Aluno extends Pessoa  implements Serializable{
	
		private static final long serialVersionUID = 1L;
				
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name = "id_aluno")
		private Long id;
		
		@NotBlank(message="Campo matricula obrigatorio")
		@Column(nullable = false)
		private String matricula;		
		
		@OneToOne(cascade = CascadeType.ALL)
		private Responsavel responsavel;
			
		@OneToOne(cascade = CascadeType.ALL)
		private Telefone telefone;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "id_turma",nullable = true)
		private Turma turma;
	  	
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMatricula() {
			return matricula;
		}

		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}

		public Responsavel getResponsavel() {
			return responsavel;
		}

		public void setResponsavel(Responsavel responsavel) {
			this.responsavel = responsavel;
		}

		public Telefone getTelefone() {
			return telefone;
		}

		public void setTelefone(Telefone telefone) {
			this.telefone = telefone;
		}

		public Turma getTurma() {
			return turma;
		}

		public void setTurma(Turma turma) {
			this.turma = turma;
		}		
		
		
}
	



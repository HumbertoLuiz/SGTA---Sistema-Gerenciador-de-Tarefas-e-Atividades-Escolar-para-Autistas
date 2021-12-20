package br.edu.ifpr.sgta.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


@Entity(name = "TB_ALUNO")
public class Aluno extends Pessoa  implements Serializable{
	
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
		
		@NotBlank(message="Campo matricula obrigatorio")
		@Column(nullable = false)
		private Integer matricula;		
								
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Integer getMatricula() {
			return matricula;
		}

		public void setMatricula(Integer matricula) {
			this.matricula = matricula;
		}
				
	}
	



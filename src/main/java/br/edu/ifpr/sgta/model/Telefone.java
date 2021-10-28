package br.edu.ifpr.sgta.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "TB_TELEFONE")
public class Telefone implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String fixo;
	
	private String Celular;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFixo() {
		return fixo;
	}
	public void setFixo(String fixo) {
		this.fixo = fixo;
	}
	public String getCelular() {
		return Celular;
	}
	public void setCelular(String celular) {
		Celular = celular;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	

}

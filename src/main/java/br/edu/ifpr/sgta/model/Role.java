package br.edu.ifpr.sgta.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


import br.edu.ifpr.sgta.controller.UsuarioSerializer;

@Entity(name = "TB_ROLE")
public class Role implements GrantedAuthority{
	
		
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Campo nome obrigatorio")
	@Size(min = 2, max = 50, message = "Nome deve conter {min} ate {max} caracteres")
	@Column(nullable = false)
	private String nome;
	
	@JsonSerialize(using = UsuarioSerializer.class)
	@ManyToMany(fetch=FetchType.EAGER, mappedBy = "roles")
	private Set<Usuario> usuarios;
	
	public Role() {	}
	
	public Role(String nome) {
		this.nome = nome;
	}		
	
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
	
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String getAuthority() {
		return this.nome;
	}
	
	

}

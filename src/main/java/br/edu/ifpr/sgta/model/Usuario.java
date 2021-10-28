package br.edu.ifpr.sgta.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import br.edu.ifpr.sgta.controller.RoleSerializer;

@Entity(name = "TB_USUARIO")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="Campo email obrigatorio")
	@Email(message = "email invalido")
	@Column(nullable = false, unique=true)
	private String email;	
	
	@Column(nullable = false)
	private String senha;
	
	private boolean ativo;
	
	@JsonSerialize(using = RoleSerializer.class)
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="usuario_role",
		joinColumns = {@JoinColumn(name="usuario_id")}, 
		inverseJoinColumns = {@JoinColumn(name="role_id")}) 
	private Set<Role> roles;
	
	@OneToOne(mappedBy = "usuario", orphanRemoval = true, cascade = CascadeType.ALL)
	private Pessoa pessoa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha; 
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}		
		
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Usuario() {}
	
	public Usuario( String email, String senha,  Role role) {
		this.email = email;
		this.senha = senha;		
		if (role!=null) {
			this.roles = new HashSet<>();
			this.roles.add(role);
		}
	}
	
	public Usuario( String email, String senha,  List<Role> roles) {
		
		this.email = email;
		this.senha = senha;		
		if (roles!=null && roles.size() > 0) {
			this.roles = new HashSet<>();
			this.roles.addAll(roles);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}	
	
	

	
}
	
	

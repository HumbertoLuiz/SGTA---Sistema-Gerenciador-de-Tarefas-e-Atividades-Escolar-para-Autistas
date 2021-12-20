package br.edu.ifpr.sgta.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ifpr.sgta.model.Role;
import br.edu.ifpr.sgta.model.Usuario;

public class UsuarioDetails implements UserDetails { 


	private static final long serialVersionUID = 1L;
	private String email; 
	private String senha;
	private Set<Role> roles;
		
    private List<GrantedAuthority> authorities  = new ArrayList<GrantedAuthority>();; 

    public UsuarioDetails(Usuario user) {
		this.email = user.getEmail();
		this.senha = user.getSenha();
		this.roles = user.getRoles();
		
		for(Role roleUser : user.getRoles()){
			String nome = roleUser.getNome();
			this.authorities.add(new SimpleGrantedAuthority(nome));	
		}
	}
        
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	@Override
	public String getPassword() {
		return this.senha;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	
	public Set<Role> getRoles() {
		return this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO - get from database
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO - get from database
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO - get from database
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO - get from database
		return true;
	}
}

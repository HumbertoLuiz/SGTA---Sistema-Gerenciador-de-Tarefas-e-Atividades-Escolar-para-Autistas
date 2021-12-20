package br.edu.ifpr.app.sae.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ifpr.app.sae.model.Role;
import br.edu.ifpr.app.sae.model.Usuario;

public class UsuarioDetails implements UserDetails { 
	
	private static final long serialVersionUID = 1L;
	private String email; 
	private String password;
	private Collection<Role> roles;
		
    private List<GrantedAuthority> authorities  = new ArrayList<GrantedAuthority>();; // roles (SUPERADMIN, ADMIN, USER, VISITOR, ...)

    public UsuarioDetails(Usuario user) {
		this.email = user.getEmail();
		this.password = user.getSenha();
		this.roles = user.getRoles();
		
		for(Role roleUser : user.getRoles()){
			String role = roleUser.getNome();
			this.authorities.add(new SimpleGrantedAuthority(role));	
		}
	}
        
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	@Override
	public String getPassword() {
		return this.password;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	
	public Collection<Role> getRoles() {
		return this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	@Override
	public boolean isEnabled() {
		
		return true;
	}
}

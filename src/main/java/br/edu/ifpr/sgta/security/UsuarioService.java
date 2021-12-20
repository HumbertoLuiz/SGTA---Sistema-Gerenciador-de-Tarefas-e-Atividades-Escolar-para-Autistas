package br.edu.ifpr.sgta.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifpr.sgta.model.Usuario;
import br.edu.ifpr.sgta.repository.UsuarioRepository;


@Service
public class UsuarioService implements UserDetailsService {

	
	@Autowired
	UsuarioRepository dao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario user = dao.findByEmail(email);
		if (user==null) {
			throw new UsernameNotFoundException("Login invalido");
		}
		UsuarioDetails usd = new UsuarioDetails(user);
		return usd;

	}			
		    	
	
}



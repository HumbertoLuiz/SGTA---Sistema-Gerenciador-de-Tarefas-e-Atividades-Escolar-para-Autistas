package br.edu.ifpr.sgta.repository;

import java.util.ArrayList;

import java.util.List;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import br.edu.ifpr.sgta.model.Role;
import br.edu.ifpr.sgta.model.Usuario;



@Service
public class DataLoaderHelper {
	
	public static void loadData(
			
			UsuarioRepository daoUser,
			RoleRepository daoRole			
			)
	{
				
					
									
						
		List<Role> roleList = new ArrayList<>();
		roleList.add(new Role("ROLE_ADMIN"));
		roleList.add(new Role("ROLE_USER"));
		roleList.add(new Role("ROLE_GUEST"));
		daoRole.saveAll(roleList);
		
		/* Exemplo para salvar o usuario com senha criptografada */
	BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		
		List<Usuario> userList = new ArrayList<>();
		userList.add(new Usuario( "maria90@test.org", passEncoder.encode("123"),  daoRole.findByNome("ROLE_USER")));
		userList.add(new Usuario( "admin@test.org",  passEncoder.encode("123"), daoRole.findByNome("ROLE_ADMIN")));
		userList.add(new Usuario( "guest@test.org",  passEncoder.encode("123"), daoRole.findByNome("ROLE_GUEST")));
		daoUser.saveAll(userList);
		
	}
	
	@Bean
	public CommandLineRunner loader( UsuarioRepository daoUser, RoleRepository daoRole) {
		return (args) -> {
			DataLoaderHelper.loadData( daoUser, daoRole);
		};
	}
}

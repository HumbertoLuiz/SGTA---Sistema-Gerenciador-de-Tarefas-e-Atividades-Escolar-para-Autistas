package br.edu.ifpr.sgta.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			//.csrf().disable() //Habilita method Post, #issue verificar como configurar method Post			
			.antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll()				
			.anyRequest().authenticated()																		
			//.antMatchers(HttpMethod.GET,"/usuario/**").hasRole("ADMIN")
		//	.antMatchers(HttpMethod.POST,"/usuario/insertUsuario").hasRole("ADMIN")
			
			.and()
				.formLogin()
					.loginPage("/login").permitAll() //free page login for everyone
					.defaultSuccessUrl("/", true)
			.and()
				.rememberMe() // Habilita a função de "lembrar-me"
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
			.and()
				.httpBasic();
		
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/webjars/**");
	}
	
	@Autowired
	private UsuarioService userDetailsService;

}

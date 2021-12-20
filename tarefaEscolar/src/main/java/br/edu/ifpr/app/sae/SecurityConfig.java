package br.edu.ifpr.app.sae;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.edu.ifpr.app.sae.service.UsuarioService;

//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
		
	
	@Autowired
	private UsuarioService userServ;	
	 		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userServ);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}		
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .csrf()
	        .disable()
	        .authorizeRequests()
	        .antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll()				
			.antMatchers("/register/**", "/login/**", "/forgot-password/**",
					"/successfulRegisteration/**","/confirm-account/**",
					"/successForgotPassword/**","/confirm-reset/**",
					"/reset-password/**","/error/**","/resetPassword/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/login").permitAll() //free page login for everyone
		//	.defaultSuccessUrl("/", true)
	.and()
		.rememberMe() // Habilita a função de "lembrar-me"
	.and()
		.logout()		
	.and()
		.httpBasic();
	     
		    http.headers().frameOptions().disable();
}
			
		public void configure(WebSecurity web) throws Exception{
			web.ignoring().antMatchers("/webjars/**");
		}
			
}

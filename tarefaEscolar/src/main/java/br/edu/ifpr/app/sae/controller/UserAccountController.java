package br.edu.ifpr.app.sae.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpr.app.sae.model.ConfirmationToken;
import br.edu.ifpr.app.sae.model.Usuario;
import br.edu.ifpr.app.sae.repository.ConfirmationTokenRepository;
import br.edu.ifpr.app.sae.repository.RoleRepository;
import br.edu.ifpr.app.sae.repository.UsuarioRepository;
import br.edu.ifpr.app.sae.service.EmailSenderService;

@Controller
public class UserAccountController {
	public static final String USER_ROLE = "ROLE_USER";
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;

	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView displayRegistration(ModelAndView modelAndView, Usuario user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerUser(ModelAndView modelAndView, Usuario user) {
		
		Usuario existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
		if(existingUser != null) {
			modelAndView.addObject("message","Email ja cadastrado!");
			modelAndView.setViewName("error");
		} else {
			user.setSenha(encoder.encode(user.getSenha()));
			 user.setRoles(Arrays.asList(roleRepository.findByNome(USER_ROLE)));
			userRepository.save(user);
			
			ConfirmationToken confirmationToken = new ConfirmationToken(user);
			
			confirmationTokenRepository.save(confirmationToken);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("Complete seu  Registro!");
			mailMessage.setFrom("sistematarefasgte@gmail.com");
			mailMessage.setText("Confirme sua conta , clique aqui: "
			+"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
			
			emailSenderService.sendEmail(mailMessage);
			
			modelAndView.addObject("email", user.getEmail());
			
			modelAndView.setViewName("successfulRegisteration");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		
		if(token != null)
		{
			Usuario user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
			user.setEnabled(true);
			userRepository.save(user);
			modelAndView.setViewName("accountVerified");
		}
		else
		{
			modelAndView.addObject("message","Este link e invalido ou vencido!");
			modelAndView.setViewName("error");
		}
		
		return modelAndView;
	}	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView displayLogin(ModelAndView modelAndView, Usuario user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView loginUser(ModelAndView modelAndView, Usuario user) {
		
		Usuario existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
		if(existingUser != null) {
			
			if (encoder.matches(user.getSenha(), existingUser.getSenha())){
				
				modelAndView.addObject("message", "Logado com sucesso!");
				modelAndView.setViewName("successLogin");
			} else {
				
				modelAndView.addObject("message", "Senha incorreta. Digite novamente.");
				modelAndView.setViewName("login");
			}
		} else {	
			modelAndView.addObject("message", "Este email nao esta cadastrado!");
			modelAndView.setViewName("login");

		}
		
		return modelAndView;
	}	
	
	@RequestMapping(value="/forgot-password", method=RequestMethod.GET)
	public ModelAndView displayResetPassword(ModelAndView modelAndView, Usuario user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("forgotPassword");
		return modelAndView;
	}
	
	@RequestMapping(value="/forgot-password", method=RequestMethod.POST)
	public ModelAndView forgotUserPassword(ModelAndView modelAndView, Usuario user) {
		Usuario existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
		if(existingUser != null) {
		
			ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);
					
			confirmationTokenRepository.save(confirmationToken);
						
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(existingUser.getEmail());
			mailMessage.setSubject("Complete Password Reset!");
			mailMessage.setFrom("sistematarefasgte@gmail.com");
			mailMessage.setText("Para troca de sua senha, click aqui: "
			+"http://localhost:8080/confirm-reset?token="+confirmationToken.getConfirmationToken());
			
			emailSenderService.sendEmail(mailMessage);

			modelAndView.addObject("message", "Trco de senha efetuada  com sucesso. Verifique seu email.");
			modelAndView.setViewName("successForgotPassword");

		} else {	
			modelAndView.addObject("message", "Email não cadastrado!");
			modelAndView.setViewName("error");
		}
		
		return modelAndView;
	}

	@RequestMapping(value="/confirm-reset", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView validateResetToken(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
	{
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		
		if(token != null) {
			Usuario user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
			user.setEnabled(true);
			userRepository.save(user);
			modelAndView.addObject("user", user);
			modelAndView.addObject("email", user.getEmail());
			modelAndView.setViewName("resetPassword");
		} else {
			modelAndView.addObject("message", "Link invalido ou vencido!");
			modelAndView.setViewName("error");
		}
		
		return modelAndView;
	}	

	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public ModelAndView resetUserPassword(ModelAndView modelAndView, Usuario user) {
				
		if(user.getEmail() != null) {
			
			Usuario tokenUser = userRepository.findByEmailIgnoreCase(user.getEmail());
			tokenUser.setEnabled(true);
			tokenUser.setSenha(encoder.encode(user.getSenha()));
			
			userRepository.save(tokenUser);
			modelAndView.addObject("message", "Senha trocada com sucesso. Agora você pode fazer login com as novas credenciais.");
			modelAndView.setViewName("successResetPassword");
		} else {
			modelAndView.addObject("message","Link invalido ou vencido!");
			modelAndView.setViewName("error");
		}
		
		return modelAndView;
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/usuario/list")
	public String list(Model model) {
		List<Usuario> usuarios = userRepository.findAll();
		model.addAttribute("userList", usuarios);
		return "usuario/listUsuario.html";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/usuario/editUsuario/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Usuario user  = userRepository.findById(id).get();
		model.addAttribute("usuario", user);
		return "register.html";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/usuario/delete/{id}")
	public String delete(@PathVariable Long id) {
		userRepository.removeUsuario(id);
		return "redirect:/usuario/list";
	}

	public UsuarioRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UsuarioRepository userRepository) {
		this.userRepository = userRepository;
	}

	public ConfirmationTokenRepository getConfirmationTokenRepository() {
		return confirmationTokenRepository;
	}

	public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
		this.confirmationTokenRepository = confirmationTokenRepository;
	}

	public EmailSenderService getEmailSenderService() {
		return emailSenderService;
	}

	public void setEmailSenderService(EmailSenderService emailSenderService) {
		this.emailSenderService = emailSenderService;
	}
	
}
package br.edu.ifpr.sgta.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifpr.sgta.model.Usuario;
import br.edu.ifpr.sgta.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	UsuarioRepository daoUser;

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "usuario/insertUsuario.html";
	}
	

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Usuario usuario = daoUser.findById(id).get();
		model.addAttribute("usuario", usuario);
		return "usuario/insertUsuario.html";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid Usuario usuario, BindingResult result, Model model,
			@RequestParam("senha") String Senha) {
				
		usuario.setSenha(passwordEncoder.encode(Senha));
		daoUser.save(usuario);
		return "redirect:/usuarios/list";
	}
		
	@GetMapping("/list")
	public String list(Model model) {
		List<Usuario> usuarios = daoUser.findAll();
		model.addAttribute("userList", usuarios);
		return "usuario/listUsuario.html";
	}

	/*@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		daoUser.removerUsuario(id);
		return "redirect:/usuarios/list";
	}*/

}

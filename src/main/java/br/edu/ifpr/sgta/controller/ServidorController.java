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

import br.edu.ifpr.sgta.model.Servidor;
import br.edu.ifpr.sgta.repository.ServidorRepository;

@Controller
@RequestMapping("/servidores")
public class ServidorController {

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	ServidorRepository daoServidor;

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Servidor servidor = new Servidor();
		model.addAttribute("servidor", servidor);
		return "servidor/insertServidor.html";
	}
	

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Servidor servidor = daoServidor.findById(id).get();
		model.addAttribute("servidor", servidor);
		return "servidor/insertServidor.html";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid Servidor servidor, BindingResult result, Model model) {
				
		
		daoServidor.save(servidor);
		return "redirect:/servidores/list";
	}
		
	@GetMapping("/list")
	public String list(Model model) {
		List<Servidor> servidores = daoServidor.findAll();
		model.addAttribute("userList", servidores);
		return "servidor/listServidor.html";
	}

	/*@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		daoServidor.removerServidor(id);
		return "redirect:/servidores/list";
	}*/

}
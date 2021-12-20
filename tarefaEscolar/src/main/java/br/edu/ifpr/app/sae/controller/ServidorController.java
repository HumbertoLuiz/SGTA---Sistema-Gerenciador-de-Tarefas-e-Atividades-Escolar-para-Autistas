package br.edu.ifpr.app.sae.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpr.app.sae.model.Servidor;
import br.edu.ifpr.app.sae.repository.ServidorRepository;

@Controller
@RequestMapping("/servidor")
public class ServidorController {	

	@Autowired
	ServidorRepository daoServidor;

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Servidor servidor = new Servidor();
		model.addAttribute("servidor", servidor);
		return "servidor/insertServidor.html";
	}	

	@Secured("ROLE_ADMIN")
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
		return "redirect:/servidor/list";
	}
	
	@Secured("ROLE_ADMIN")	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView listarServidor() {
		ModelAndView mv= new ModelAndView("servidor/listServidor");
		mv.addObject("servidors", daoServidor.findAll());
		mv.addObject("servidor", new Servidor());
		return mv;
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		daoServidor.removeServidor(id);
		return "redirect:/servidor/list";
	}

}
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


import br.edu.ifpr.sgta.model.Disciplina;
import br.edu.ifpr.sgta.repository.DisciplinaRepository;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	DisciplinaRepository daoDisciplina;

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Disciplina disciplina = new Disciplina();
		model.addAttribute("disciplina", disciplina);
		return "disciplina/insertDisciplina.html";
	}
	

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Disciplina disciplina = daoDisciplina.findById(id).get();
		model.addAttribute("disciplina", disciplina);
		return "disciplina/insertDisciplina.html";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid Disciplina disciplina, BindingResult result, Model model) {
				
		
		daoDisciplina.save(disciplina);
		return "redirect:/disciplinas/list";
	}
		
	@GetMapping("/list")
	public String list(Model model) {
		List<Disciplina> disciplinas = daoDisciplina.findAll();
		model.addAttribute("userList", disciplinas);
		return "disciplina/listDisciplina.html";
	}

	/*@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		daoDisciplina.removerDisciplina(id);
		return "redirect:/disciplinas/list";
	}*/

}


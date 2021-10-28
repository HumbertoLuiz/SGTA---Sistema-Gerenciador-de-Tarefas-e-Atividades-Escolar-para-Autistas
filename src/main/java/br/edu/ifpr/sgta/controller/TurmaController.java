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


import br.edu.ifpr.sgta.model.Turma;
import br.edu.ifpr.sgta.repository.TurmaRepository;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	TurmaRepository daoTurma;

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Turma turma = new Turma();
		model.addAttribute("turma", turma);
		return "turma/insertTurma.html";
	}
	

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Turma turma = daoTurma.findById(id).get();
		model.addAttribute("turma", turma);
		return "turma/insertTurma.html";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid Turma turma, BindingResult result, Model model) {
				
		
		daoTurma.save(turma);
		return "redirect:/turmas/list";
	}
		
	@GetMapping("/list")
	public String list(Model model) {
		List<Turma> turmas = daoTurma.findAll();
		model.addAttribute("userList", turmas);
		return "turma/listTurma.html";
	}

	/*@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		daoTurma.removerTurma(id);
		return "redirect:/turmas/list";
	}*/

}


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


import br.edu.ifpr.sgta.model.Aluno;
import br.edu.ifpr.sgta.repository.AlunoRepository;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	AlunoRepository daoAluno;

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Aluno aluno = new Aluno();
		model.addAttribute("aluno", aluno);
		return "aluno/insertAluno.html";
	}
	

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Aluno aluno = daoAluno.findById(id).get();
		model.addAttribute("aluno", aluno);
		return "aluno/insertAluno.html";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid Aluno aluno, BindingResult result, Model model) {
				
		
		daoAluno.save(aluno);
		return "redirect:/alunos/list";
	}
		
	@GetMapping("/list")
	public String list(Model model) {
		List<Aluno> alunos = daoAluno.findAll();
		model.addAttribute("userList", alunos);
		return "aluno/listAluno.html";
	}

	/*@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		daoAluno.removerAluno(id);
		return "redirect:/alunos/list";
	}*/

}



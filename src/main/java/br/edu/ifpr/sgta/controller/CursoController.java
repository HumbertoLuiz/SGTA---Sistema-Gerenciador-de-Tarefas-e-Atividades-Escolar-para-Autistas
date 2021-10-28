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


import br.edu.ifpr.sgta.model.Curso;
import br.edu.ifpr.sgta.repository.CursoRepository;

@Controller
@RequestMapping("/cursos")
public class CursoController {

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	CursoRepository daoCurso;

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Curso curso = new Curso();
		model.addAttribute("curso", curso);
		return "curso/insertCurso.html";
	}
	

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Curso curso = daoCurso.findById(id).get();
		model.addAttribute("curso", curso);
		return "curso/insertCurso.html";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid Curso curso, BindingResult result, Model model) {
				
		
		daoCurso.save(curso);
		return "redirect:/cursos/list";
	}
		
	@GetMapping("/list")
	public String list(Model model) {
		List<Curso> cursos = daoCurso.findAll();
		model.addAttribute("userList", cursos);
		return "curso/listCurso.html";
	}

	/*@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		daoCurso.removerCurso(id);
		return "redirect:/cursos/list";
	}*/

}

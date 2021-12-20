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

import br.edu.ifpr.app.sae.model.Curso;
import br.edu.ifpr.app.sae.repository.CursoRepository;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	CursoRepository daoCurso;
	 
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Curso curso = new Curso();
		model.addAttribute("curso", curso);
		return "curso/insertCurso.html";
	}		

	@Secured("ROLE_ADMIN")
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
		return "redirect:/curso/list";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView listarCurso() {
		ModelAndView mv= new ModelAndView("curso/listCurso");
		mv.addObject("cursos", daoCurso.findAll());
		mv.addObject("curso", new Curso());
		return mv;
	}
	
	@Secured("ROLE_ADMIN")
	public String addCurso(Curso curso, Model model) {
		model.addAttribute("cursos", daoCurso.findAll());
		return "addCurso";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		daoCurso.removerCurso(id);
		return "redirect:/cursos/list";
	}

}
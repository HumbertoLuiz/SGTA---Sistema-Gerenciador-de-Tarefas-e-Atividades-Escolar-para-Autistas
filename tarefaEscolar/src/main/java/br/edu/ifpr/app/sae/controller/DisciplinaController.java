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

import br.edu.ifpr.app.sae.model.Disciplina;
import br.edu.ifpr.app.sae.repository.DisciplinaRepository;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {
	

	@Autowired
	DisciplinaRepository daoDisciplina;

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Disciplina disciplina = new Disciplina();
		model.addAttribute("disciplina", disciplina);
		return "disciplina/insertDisciplina.html";
	}	

	@Secured("ROLE_ADMIN")
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
		return "redirect:/disciplina/list";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView listarDisciplina() {
		ModelAndView mv= new ModelAndView("disciplina/listDisciplina");
		mv.addObject("disciplinas", daoDisciplina.findAll());
		mv.addObject("disciplina", new Disciplina());
		return mv;
	}
	
}


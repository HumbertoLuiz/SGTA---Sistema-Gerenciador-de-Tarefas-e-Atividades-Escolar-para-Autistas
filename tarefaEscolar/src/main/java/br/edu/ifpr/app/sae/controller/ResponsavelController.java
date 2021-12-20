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

import br.edu.ifpr.app.sae.model.Aluno;
import br.edu.ifpr.app.sae.model.Responsavel;
import br.edu.ifpr.app.sae.repository.AlunoRepository;
import br.edu.ifpr.app.sae.repository.ResponsavelRepository;

@Controller
@RequestMapping("responsavel")
public class ResponsavelController {
	
	@Autowired
	private ResponsavelRepository responsaveles;
	
	@Autowired
	private AlunoRepository daoAluno;
	
	@GetMapping("/editResponsavel")
	public String form() {
		return "responsavel/editResponsavel";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid Aluno aluno, BindingResult result, Model model) {
						
		daoAluno.save(aluno);
		return "redirect:/responsavel/list";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Aluno aluno = daoAluno.findById(id).get();
		model.addAttribute("aluno", aluno);
		return " redirect:/responsavel/editResponsavel.html";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView listarResponsavel() {
		ModelAndView mv= new ModelAndView("responsavel/listResponsavel");
		mv.addObject("responsaveles", responsaveles.findAll());
		mv.addObject("reponsavel", new Responsavel());
		return mv;
	}
	

}
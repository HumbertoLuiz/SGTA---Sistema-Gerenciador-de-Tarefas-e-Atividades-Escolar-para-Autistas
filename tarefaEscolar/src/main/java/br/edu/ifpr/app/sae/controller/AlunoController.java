package br.edu.ifpr.app.sae.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpr.app.sae.model.Aluno;
import br.edu.ifpr.app.sae.model.Turma;
import br.edu.ifpr.app.sae.repository.AlunoRepository;
import br.edu.ifpr.app.sae.repository.ResponsavelRepository;
import br.edu.ifpr.app.sae.service.TurmaService;

@Controller
@RequestMapping("/aluno")
public class AlunoController {	

	@Autowired
	AlunoRepository daoAluno;
	
	@Autowired
	ResponsavelRepository daoResponsavel;
	
	@Autowired
	private TurmaService turmaService;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Aluno aluno = new Aluno();
		model.addAttribute("aluno", aluno);
		return "aluno/insertAluno.html";
	}

	@Secured("ROLE_ADMIN")	
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
		return "redirect:/aluno/list";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView listarAluno() {
		ModelAndView mv= new ModelAndView("aluno/listAluno");
		mv.addObject("alunos", daoAluno.findAll());
		mv.addObject("aluno", new Aluno());
		return mv;
	}	
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		daoAluno.removerAluno(id);
		return "redirect:/aluno/list";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="listResp", method=RequestMethod.GET)
	public ModelAndView listarResponsavel() {
		ModelAndView mv= new ModelAndView("aluno/listResponsavel");
		mv.addObject("alunos", daoAluno.findAll());
		mv.addObject("aluno", new Aluno());
		return mv;
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/edit1/{id}")
	public String editResp(@PathVariable Long id, Model model) {
		Aluno aluno = daoAluno.findById(id).get();
		model.addAttribute("aluno", aluno);
		return "aluno/editResponsavel.html";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/saveResp")
	public String saveResponsavel(@Valid Aluno aluno, BindingResult result, Model model) {		
		
		daoAluno.save(aluno);
		return "redirect:/aluno/listResp";
	}
	
	@Secured("ROLE_ADMIN")
	@ModelAttribute("turmas")
	public List<Turma> listaDeTurmas() {
		return turmaService.findAllTurmas();
	}	

}



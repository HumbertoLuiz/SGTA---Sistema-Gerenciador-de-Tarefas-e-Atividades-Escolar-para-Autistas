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

import  br.edu.ifpr.app.sae.model.Disciplina;
import  br.edu.ifpr.app.sae.model.Tarefa;
import br.edu.ifpr.app.sae.model.Turma;
import br.edu.ifpr.app.sae.repository.AlunoRepository;
import br.edu.ifpr.app.sae.repository.DisciplinaRepository;
import  br.edu.ifpr.app.sae.repository.TarefaRepository;
import br.edu.ifpr.app.sae.repository.TurmaRepository;
import br.edu.ifpr.app.sae.repository.UsuarioRepository;
import  br.edu.ifpr.app.sae.service.DisciplinaService;
import br.edu.ifpr.app.sae.service.TurmaService;

@Controller
@RequestMapping("tarefa")
public class TarefaController {
	
	@Autowired
	TarefaRepository daoTarefa;
	
	@Autowired
	DisciplinaRepository daoDisc;
	
	@Autowired
	AlunoRepository daoAluno;
	
	@Autowired
	TurmaRepository daoTurma;
	
	@Autowired
	UsuarioRepository daoUsuario;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private TurmaService turmaService;
		
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Tarefa tarefa = new Tarefa();
		model.addAttribute("tarefa", tarefa);
		return "tarefa/insertTarefa.html";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Tarefa tarefa = daoTarefa.findById(id).get();
		model.addAttribute("tarefa", tarefa);
		return "tarefa/insertTarefa.html";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid Tarefa tarefa, BindingResult result, Model model) {				
		
		daoTarefa.save(tarefa);
		return "redirect:/tarefa/list";
	}
	
	//@Secured("ROLE_ADMIN")	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView listarTarefa() {
		ModelAndView mv= new ModelAndView("tarefa/listTarefa");
		mv.addObject("tarefas", daoTarefa.findAll());
		mv.addObject("tarefa", new Tarefa());
		return mv;
	}	

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		daoTarefa.removeTarefa(id);
		return "redirect:/tarefas/list";
	}	
	
	@ModelAttribute("disciplinas")
	public List<Disciplina> listaDeDisciplinas() {
		return disciplinaService.buscarTodos();
	}		
	
	@ModelAttribute("turmas")
	public List<Turma> listaDeTurmas() {
		return turmaService.findAllTurmas();
	}	
	
}

package br.edu.ifpr.sgta.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
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

import br.edu.ifpr.sgta.model.Tarefa;
import br.edu.ifpr.sgta.repository.TarefaRepository;

@Controller
@RequestMapping("agendas")
public class TarefaController {
	
	@Autowired
	TarefaRepository daoTarefa;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Tarefa tarefa = new Tarefa();
		model.addAttribute("tarefa", tarefa);
		return "tarefa/insertTarefa.html";
	}
	

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
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
		return "redirect:/tarefas/list";
	}
		
	@GetMapping("/list")
	public String list(Model model) {
		List<Tarefa> tarefas = daoTarefa.findAll();
		model.addAttribute("userList", tarefas);
		return "tarefa/listTarefa.html";
	}

	/*@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		daoTarefa.removerTarefa(id);
		return "redirect:/tarefas/list";
	}*/

}

package br.edu.ifpr.app.sae.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifpr.app.sae.model.Disciplina;
import br.edu.ifpr.app.sae.model.Professor;
import br.edu.ifpr.app.sae.model.Turma;
import br.edu.ifpr.app.sae.repository.DisciplinaRepository;
import br.edu.ifpr.app.sae.repository.TurmaRepository;
import br.edu.ifpr.app.sae.service.TurmaService;

@Controller
@RequestMapping("/turma")
public class TurmaController {
	
	@Autowired
	private TurmaService turmaService;

	@Autowired
	DisciplinaRepository disciplinaRepository;

	@Autowired
	TurmaRepository turmaRepository;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String index(Model model) {
		model.addAttribute("turma", turmaService.createTurma());
		return "turma/insertTurma";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid Turma turma, BindingResult bindingResult, Model model) {
		int i=1;
		if (bindingResult.hasErrors()) {
			model.addAttribute("errorMessage", "The submitted data has errors.");
		} else {
			for (Disciplina cnct : turma.getDisciplinaList()) {
				cnct.setSequenceNumber(i);
				i++;
			}
			if (bindingResult.hasErrors()) {
				model.addAttribute("errorMessage", "The submitted data has errors.");
			} else {
				for (Professor cncp : turma.getProfessorList()) {
					cncp.setSequenceNumber(i);
					i++;
				}
			}	
			
			model.addAttribute("turma", turmaService.saveTurma(turma));
			model.addAttribute("successMessage", "Turma saved successfully!");
		}

		return "redirect:/turma/list";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = { "list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		List<Turma> turmas = turmaService.findAllTurmas();
		model.addAttribute("turmas", turmas);
		model.addAttribute("metaTitle", "All Users");
		return "turma/list";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/addRow")
	public String addRow(Turma turma) {
		turmaService.addRow(turma);
		return "turma/insertTurma :: disciplinas"; 
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/removeDisciplina")
	public String removeDisciplina(Turma turma, @RequestParam("removeDynamicRow") Integer disciplinaIndex) {
		turmaService.removeDisciplina(turma, disciplinaIndex);
		return "insertTurma :: disciplinas"; 
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/turmaDelete/{id}", method = RequestMethod.GET)
	public String notesDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
		turmaRepository.deleteById(id);

		return "redirect:/turma/list";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		turmaRepository.deleteById(id);
		return "redirect:/turma/list";
	}
	
	@Secured("ROLE_ADMIN")
	@ModelAttribute("turmas")
	public List<Turma> listaDeTurmas() {
		return turmaService.findAllTurmas();
	}	
	
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/editTurma/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Turma turma = turmaRepository.findById(id).get();
		model.addAttribute("turma", turma);
		return "turma/insertTurma.html";
	}
}


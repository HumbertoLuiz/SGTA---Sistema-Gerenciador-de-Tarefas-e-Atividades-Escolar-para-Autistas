package br.edu.ifpr.sgta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpr.sgta.model.Responsavel;
import br.edu.ifpr.sgta.repository.ResponsavelRepository;

@Controller
@RequestMapping("responsavel")
public class ResponsavelController {
	
	@Autowired
	private ResponsavelRepository responsaveles;
	
	////Mapeamento da pagina insertResponsavel
	@GetMapping("/insertResponsavel")
	public String form() {
		return "responsavel/insertResponsavel";
	}
	
	//POST do formulario insertResponsavel
	@PostMapping("/insertResponsavel")
	public String form(Responsavel responsavel) {
		responsaveles.save(responsavel);
		return "redirect:/insertResponsavel";
	}
	
	//Mapeamento da pagina listResponsavel
	@GetMapping("/listResponsavel")
	public ModelAndView listarResponsavel() {
		ModelAndView mv = new ModelAndView("responsavel/listResponsavel");
		mv.addObject("responsaveles", responsaveles.findAll());
		mv.addObject("responsavel", new Responsavel());
		return mv;
	}
}
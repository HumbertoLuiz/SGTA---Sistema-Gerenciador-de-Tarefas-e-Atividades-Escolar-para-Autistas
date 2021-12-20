package br.edu.ifpr.sgta.controller;

/*import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpr.sgta.dao.IPessoaService;
import br.edu.ifpr.sgta.model.Pessoa;



/*@Controller
@SessionAttributes("pessoa")
public class PessoaController {
	
	@Autowired
//	private IPessoaService pessoaService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model)
	{
		model.addAttribute("titulo", "Listado de pessoas");
	//	model.addAttribute("pessoas", pessoaService.findAll());
		
		return "listar";
	}
	
	@RequestMapping(value = "/formPessoa")
	public String inserir(Map<String, Object> model)
	{			
		Pessoa pessoa = new Pessoa() {
		};
			
		model.put("pessoa", pessoa);
		model.put("titulo", "Formulario de Registro");
		
		
		return "formPessoa";
	}
	
	@RequestMapping(value = "/formPessoa/{id}")
	public String editar(@PathVariable(value="id") long id,Map<String, Object> model, RedirectAttributes flash)
	{
		Pessoa pessoa = null;
		
		if(id>0)
		{
		
	//		pessoa = pessoaService.findOne(id);
			
			if( pessoa == null)
			{
				flash.addFlashAttribute("error", "Error, el id de la pessoa no existe en la base de datos");
				return "redirect:/listar";
			}
		}
		else
		{
			
			flash.addFlashAttribute("error", "Error, el id no puede ser cero");
			return "redirect:/listar";
		}
		
		model.put("pessoa", pessoa);
		model.put("titulo", "Editar pessoa");
		
		
		return "formpessoa";
	}
	
	@RequestMapping(value ="/salvarPessoa", method = RequestMethod.POST)
	public String guardar(@Valid Pessoa pessoa, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status)
	{
		
		if(result.hasErrors())
		{
			model.addAttribute("titulo", "Formulario de Registro");
			
			return "formPessoa";
		}		
		
		String mensajeFlash = null;
		
		if(pessoa.getId() != null)
		{
			mensajeFlash = "¡A pessoa foi salva com exito!";
		}
		else if(pessoa.getId() == null)
		{
			mensajeFlash = "¡LA pessoa foi editada  exito!";
		}
					
		pessoaService.save(pessoa);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/listar";
	}
	
	@RequestMapping(value = "/excluirPessoa/{id}")
	public String eliminarpessoa(@PathVariable(value="id") long id, RedirectAttributes flash)
	{
		if(id > 0)
		{
			pessoaService.delete(id);
			flash.addFlashAttribute("error", "Pessoa excluida con exito");
		}
		
		
		return "redirect:/listar";
	}

}*/

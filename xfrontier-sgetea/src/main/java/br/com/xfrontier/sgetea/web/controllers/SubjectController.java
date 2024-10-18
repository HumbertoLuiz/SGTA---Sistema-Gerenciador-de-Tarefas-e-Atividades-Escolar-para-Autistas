package br.com.xfrontier.sgetea.web.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.xfrontier.sgetea.core.models.Subject;
import br.com.xfrontier.sgetea.web.dtos.FlashMessage;
import br.com.xfrontier.sgetea.web.dtos.SubjectDto;
import br.com.xfrontier.sgetea.web.services.SubjectService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/disciplines")
public class SubjectController {

	private SubjectService disciplineService;
	
	public SubjectController(SubjectService disciplineService) {
		this.disciplineService = disciplineService;
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Subject discipline = new Subject();
		model.addAttribute("discipline", discipline);
		return "discipline/insertSubject.html";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/discipline/discipline_list");
		modelAndView.addObject("disciplines", disciplineService.findAll());
		return modelAndView;
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/discipline/discipline_form");
		modelAndView.addObject("disciplineDto", new SubjectDto());
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("disciplineForm") SubjectDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/discipline/discipline_form";
		}
		disciplineService.save(form);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Subject registered with success!"));
		return "redirect:/admin/disciplines";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/discipline/discipline_form");
		modelAndView.addObject("disciplineForm", disciplineService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("disciplineForm") SubjectDto form,
			BindingResult result, RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/discipline/discipline_form";
		}
		disciplineService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Subject edited with success!"));
		return "redirect:/admin/disciplines";
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		disciplineService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Subject deleted with success!"));
		return "redirect:/admin/discipline";
	}

}

package br.com.nofrontier.sgetea.web.controllers;

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

import br.com.nofrontier.sgetea.core.models.Guardian;
import br.com.nofrontier.sgetea.web.dtos.FlashMessage;
import br.com.nofrontier.sgetea.web.dtos.GuardianDto;
import br.com.nofrontier.sgetea.web.services.GuardianService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("guardians")
public class GuardianController {

	private GuardianService guardianService;
	
	public GuardianController(GuardianService guardianService) {
		this.guardianService = guardianService;
	}
	
//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Guardian guardian = new Guardian();
		model.addAttribute("guardian", guardian);
		return "guardian/insertGuardian.html";
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/guardian/guardian_list");
		modelAndView.addObject("guardians", guardianService.findAll());
		return modelAndView;
	}
	
//---------------------------------------------------------------------------------------------------------

	@GetMapping("/{userId}")
	public ModelAndView findById(@PathVariable Long userId) {
		var modelAndView = new ModelAndView("/admin/guardian/guardian_list");
		modelAndView.addObject("guardians", guardianService.findById(userId));
		return modelAndView;
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/guardian/guardian_form");
		modelAndView.addObject("guardianDto", new GuardianDto());
		return modelAndView;
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("guardianForm") GuardianDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/guardian/guardian_form";
		}
		guardianService.save(form);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Guardian registered with success!"));
		return "redirect:/admin/guardians";
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/guardian/guardian_form");
		modelAndView.addObject("guardianForm", guardianService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("guardianForm") GuardianDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/guardian/guardian_form";
		}
		guardianService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Guardian edited with success!"));
		return "redirect:/admin/guardians";
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		guardianService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Guardian deleted with success!"));
		return "redirect:/admin/guardian";
	}
}
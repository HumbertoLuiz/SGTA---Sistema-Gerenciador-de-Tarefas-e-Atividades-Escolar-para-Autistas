package br.com.xfrontier.sgetea.web.controllers;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.xfrontier.sgetea.core.models.State;
import br.com.xfrontier.sgetea.web.dtos.FlashMessage;
import br.com.xfrontier.sgetea.web.dtos.StateDto;
import br.com.xfrontier.sgetea.web.services.StateService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/states")
public class StateController {

	@Autowired
	private StateService stateService;
	
	public StateController(StateService stateService) {
		this.stateService = stateService;
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		State state = new State();
		model.addAttribute("state", state);
		return "stateClass/insertStateClass.html";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/stateClass/stateClass_list");
		modelAndView.addObject("stateClasss", stateService.findAll());
		return modelAndView;
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/state/state_form");
		modelAndView.addObject("stateDto", new StateDto());
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("stateForm") StateDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/state/state_form";
		}
		stateService.save(form);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "State registered with success!"));
		return "redirect:/admin/state";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/state/state_form");
		modelAndView.addObject("stateForm", stateService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("stateForm") StateDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/state/state_form";
		}
		stateService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "State edited with success!"));
		return "redirect:/admin/state";
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		stateService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "State deleted with success!"));
		return "redirect:/admin/state";
	}

}

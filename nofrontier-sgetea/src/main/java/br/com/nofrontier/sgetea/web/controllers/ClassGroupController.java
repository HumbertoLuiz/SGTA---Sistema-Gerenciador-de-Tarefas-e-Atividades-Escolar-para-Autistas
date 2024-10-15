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

import br.com.nofrontier.sgetea.core.models.ClassGroup;
import br.com.nofrontier.sgetea.web.dtos.ClassGroupDto;
import br.com.nofrontier.sgetea.web.dtos.FlashMessage;
import br.com.nofrontier.sgetea.web.services.ClassGroupService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/classes")
public class ClassGroupController {

	private ClassGroupService classgroupService;
	
	public ClassGroupController(ClassGroupService classgroupService) {
		this.classgroupService = classgroupService;
	}
	
//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		ClassGroup courseClass = new ClassGroup();
		model.addAttribute("courseClass", courseClass);
		return "courseClass/insertClassGroup.html";
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/courseClass/courseClass_list");
		modelAndView.addObject("courseClasss", classgroupService.findAll());
		return modelAndView;
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/courseClass/courseClass_form");
		modelAndView.addObject("courseClassDto", new ClassGroupDto());
		return modelAndView;
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("courseClassForm") ClassGroupDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/courseClass/courseClass_form";
		}
		classgroupService.save(form);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "ClassGroup registered with success!"));
		return "redirect:/admin/courseClasss";
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/courseClass/courseClass_form");
		modelAndView.addObject("courseClassForm", classgroupService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("courseClassForm") ClassGroupDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/courseClass/courseClass_form";
		}
		classgroupService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "ClassGroup edited with success!"));
		return "redirect:/admin/courseClasss";
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		classgroupService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "ClassGroup deleted with success!"));
		return "redirect:/admin/courseClass";
	}

}


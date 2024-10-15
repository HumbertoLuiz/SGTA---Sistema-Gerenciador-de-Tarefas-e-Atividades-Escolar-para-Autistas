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

import br.com.nofrontier.sgetea.core.models.AdministrativeStaff;
import br.com.nofrontier.sgetea.web.dtos.AdministrativeStaffDto;
import br.com.nofrontier.sgetea.web.dtos.FlashMessage;
import br.com.nofrontier.sgetea.web.services.AdministrativeStaffService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/administrativeStaffs")
public class AdministrativeStaffController {

	private AdministrativeStaffService administrativeStaffService;
	
	public AdministrativeStaffController(AdministrativeStaffService administrativeStaffService){
		this.administrativeStaffService = administrativeStaffService;
	}
	
	

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		AdministrativeStaff administrativeStaff = new AdministrativeStaff();
		model.addAttribute("administrativeStaff", administrativeStaff);
		return "administrativeStaff/insertAdministrativeStaff.html";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/administrativeStaff/administrativeStaff_list");
		modelAndView.addObject("administrativeStaffs", administrativeStaffService.findAll());
		return modelAndView;
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/{userId}")
	public ModelAndView findById(@PathVariable Long userId) {
		var modelAndView = new ModelAndView("/admin/administrativeStaff/administrativeStaff_list");
		modelAndView.addObject("administrativeStaffs", administrativeStaffService.findById(userId));
		return modelAndView;
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/administrativeStaff/administrativeStaff_form");
		modelAndView.addObject("administrativeStaffDto", new AdministrativeStaffDto());
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("administrativeStaffForm") AdministrativeStaffDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/administrativeStaff/administrativeStaff_form";
		}
		administrativeStaffService.save(form);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "AdministrativeStaff registered with success!"));
		return "redirect:/admin/administrativeStaffs";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/administrativeStaff/administrativeStaff_form");
		modelAndView.addObject("administrativeStaffForm", administrativeStaffService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("administrativeStaffForm") AdministrativeStaffDto form,
			BindingResult result, RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/administrativeStaff/administrativeStaff_form";
		}
		administrativeStaffService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "AdministrativeStaff edited with success!"));
		return "redirect:/admin/administrativeStaffs";
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		administrativeStaffService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "AdministrativeStaff deleted with success!"));
		return "redirect:/admin/administrativeStaff";
	}
}
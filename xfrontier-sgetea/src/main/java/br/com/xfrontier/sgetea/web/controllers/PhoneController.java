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

import br.com.xfrontier.sgetea.core.models.Phone;
import br.com.xfrontier.sgetea.web.dtos.FlashMessage;
import br.com.xfrontier.sgetea.web.dtos.PhoneDto;
import br.com.xfrontier.sgetea.web.services.PhoneService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/phones")
public class PhoneController {

	@Autowired
	private PhoneService phoneService;
	
	public PhoneController(PhoneService phoneService) {
		this.phoneService = phoneService;
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Phone phone = new Phone();
		model.addAttribute("phoneClass", phone);
		return "phoneClass/insertPhoneClass.html";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/phoneClass/phoneClass_list");
		modelAndView.addObject("phoneClasss", phoneService.findAll());
		return modelAndView;
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/phone/phone_form");
		modelAndView.addObject("phoneDto", new PhoneDto());
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("phoneForm") PhoneDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/phone/phone_form";
		}
		phoneService.save(form);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Phone registered with success!"));
		return "redirect:/admin/phone";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/phone/phone_form");
		modelAndView.addObject("phoneForm", phoneService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("phoneForm") PhoneDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/phone/phone_form";
		}
		phoneService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Phone edited with success!"));
		return "redirect:/admin/phone";
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		phoneService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Phone deleted with success!"));
		return "redirect:/admin/phone";
	}

}

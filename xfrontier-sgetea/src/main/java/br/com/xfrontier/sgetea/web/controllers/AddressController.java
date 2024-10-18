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

import br.com.xfrontier.sgetea.core.models.Address;
import br.com.xfrontier.sgetea.web.dtos.AddressDto;
import br.com.xfrontier.sgetea.web.dtos.FlashMessage;
import br.com.xfrontier.sgetea.web.services.AddressService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {

	private AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Address address = new Address();
		model.addAttribute("addressClass", address);
		return "addressClass/insertAddressClass.html";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/addressClass/addressClass_list");
		modelAndView.addObject("addressClasss", addressService.findAll());
		return modelAndView;
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/address/address_form");
		modelAndView.addObject("addressDto", new AddressDto());
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("addressForm") AddressDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/address/address_form";
		}
		addressService.save(form);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Address registered with success!"));
		return "redirect:/admin/address";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/address/address_form");
		modelAndView.addObject("addressForm", addressService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("addressForm") AddressDto form,
			BindingResult result, RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/address/address_form";
		}
		addressService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Address edited with success!"));
		return "redirect:/admin/address";
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		addressService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Address deleted with success!"));
		return "redirect:/admin/address";
	}

}

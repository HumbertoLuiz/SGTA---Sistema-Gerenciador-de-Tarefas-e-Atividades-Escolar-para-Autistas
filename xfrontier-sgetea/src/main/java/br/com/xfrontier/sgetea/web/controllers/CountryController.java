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

import br.com.xfrontier.sgetea.core.models.Country;
import br.com.xfrontier.sgetea.web.dtos.CountryDto;
import br.com.xfrontier.sgetea.web.dtos.FlashMessage;
import br.com.xfrontier.sgetea.web.services.CountryService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountryController {

	private CountryService countryService;
	
	public CountryController(CountryService countryService) {
		this.countryService = countryService;
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Country country = new Country();
		model.addAttribute("country", country);
		return "countryClass/insertCountryClass.html";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/countryClass/countryClass_list");
		modelAndView.addObject("countryClasss", countryService.findAll());
		return modelAndView;
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/country/country_form");
		modelAndView.addObject("countryDto", new CountryDto());
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("countryForm") CountryDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/country/country_form";
		}
		countryService.save(form);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Country registered with success!"));
		return "redirect:/admin/country";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/country/country_form");
		modelAndView.addObject("countryForm", countryService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("countryForm") CountryDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/country/country_form";
		}
		countryService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Country edited with success!"));
		return "redirect:/admin/country";
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		countryService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Country deleted with success!"));
		return "redirect:/admin/country";
	}

}

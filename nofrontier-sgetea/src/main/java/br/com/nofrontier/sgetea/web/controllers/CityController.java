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

import br.com.nofrontier.sgetea.core.models.City;
import br.com.nofrontier.sgetea.web.dtos.CityDto;
import br.com.nofrontier.sgetea.web.dtos.FlashMessage;
import br.com.nofrontier.sgetea.web.services.CityService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {

	private CityService cityService;
	
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		City city = new City();
		model.addAttribute("city", city);
		return "cityClass/insertCityClass.html";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/cityClass/cityClass_list");
		modelAndView.addObject("cityClasss", cityService.findAll());
		return modelAndView;
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/city/city_form");
		modelAndView.addObject("cityDto", new CityDto());
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("cityForm") CityDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/city/city_form";
		}
		cityService.save(form);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "City registered with success!"));
		return "redirect:/admin/city";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/city/city_form");
		modelAndView.addObject("cityForm", cityService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("cityForm") CityDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/city/city_form";
		}
		cityService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "City edited with success!"));
		return "redirect:/admin/city";
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		cityService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "City deleted with success!"));
		return "redirect:/admin/city";
	}

}

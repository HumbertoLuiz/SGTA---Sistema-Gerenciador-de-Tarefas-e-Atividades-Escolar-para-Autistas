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
import br.com.nofrontier.sgetea.web.dtos.CourseDto;
import br.com.nofrontier.sgetea.web.dtos.FlashMessage;
import br.com.nofrontier.sgetea.web.services.CourseService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

	private CourseService courseService;
	
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		ClassGroup courseClass = new ClassGroup();
		model.addAttribute("courseClass", courseClass);
		return "courseClass/insertCourseClass.html";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/courseClass/courseClass_list");
		modelAndView.addObject("courseClasss", courseService.findAll());
		return modelAndView;
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/course/course_form");
		modelAndView.addObject("courseDto", new CourseDto());
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("courseForm") CourseDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/course/course_form";
		}
		courseService.save(form);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Course registered with success!"));
		return "redirect:/admin/course";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/course/course_form");
		modelAndView.addObject("courseForm", courseService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("courseForm") CourseDto form,
			BindingResult result, RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/course/course_form";
		}
		courseService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Course edited with success!"));
		return "redirect:/admin/course";
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		courseService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Course deleted with success!"));
		return "redirect:/admin/course";
	}

}

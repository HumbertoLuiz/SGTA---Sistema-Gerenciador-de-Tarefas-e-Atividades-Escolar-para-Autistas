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

import br.com.nofrontier.sgetea.core.models.Teacher;
import br.com.nofrontier.sgetea.web.dtos.FlashMessage;
import br.com.nofrontier.sgetea.web.dtos.TeacherDto;
import br.com.nofrontier.sgetea.web.services.TeacherService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {

	private TeacherService teacherService;
	
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		return "teacher/insertTeacher.html";
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/teacher/teacher_list");
		modelAndView.addObject("teachers", teacherService.findAll());
		return modelAndView;
	}
	
//---------------------------------------------------------------------------------------------------------

	@GetMapping("/{userId}")
	public ModelAndView findById(@PathVariable Long userId) {
		var modelAndView = new ModelAndView("/admin/teacher/teacher_list");
		modelAndView.addObject("teachers", teacherService.findById(userId));
		return modelAndView;
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/teacher/teacher_form");
		modelAndView.addObject("teacherDto", new TeacherDto());
		return modelAndView;
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("teacherForm") TeacherDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/teacher/teacher_form";
		}
		teacherService.save(form);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Teacher registered with success!"));
		return "redirect:/admin/teachers";
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/teacher/teacher_form");
		modelAndView.addObject("teacherForm", teacherService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("teacherForm") TeacherDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/teacher/teacher_form";
		}
		teacherService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Teacher edited with success!"));
		return "redirect:/admin/teachers";
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		teacherService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Teacher deleted with success!"));
		return "redirect:/admin/teacher";
	}

}



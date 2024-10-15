package br.com.nofrontier.sgetea.api.v1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import br.com.nofrontier.sgetea.api.v1.services.ApiStudentService;
import br.com.nofrontier.sgetea.core.models.Student;
import br.com.nofrontier.sgetea.web.dtos.FlashMessage;
import br.com.nofrontier.sgetea.web.dtos.StudentDto;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentRestController {

	@Autowired
	private ApiStudentService apiStudentService;

	
//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "student/insertStudent.html";
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/student/student_list");
		modelAndView.addObject("students", apiStudentService.findAll());
		return modelAndView;
	}
	
//---------------------------------------------------------------------------------------------------------

	@GetMapping("/{userId}")
	public ModelAndView findById(@PathVariable Long userId) {
		var modelAndView = new ModelAndView("/admin/student/student_list");
		modelAndView.addObject("students", apiStudentService.findById(userId));
		return modelAndView;
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/student/student_form");
		modelAndView.addObject("studentDto", new StudentDto());
		return modelAndView;
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("studentForm") StudentDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/student/student_form";
		}
		apiStudentService.save(form);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Student registered with success!"));
		return "redirect:/admin/students";
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/student/student_form");
		modelAndView.addObject("studentForm", apiStudentService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("studentForm") StudentDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/student/student_form";
		}
		apiStudentService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Student edited with success!"));
		return "redirect:/admin/students";
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		apiStudentService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Student deleted with success!"));
		return "redirect:/admin/student";
	}

	@GetMapping("/{studentId}/average-score")
	public ResponseEntity<Double> getAverageScore(@PathVariable Long studentId) {
	    Double averageScore = apiStudentService.calculateAverageScoreForStudent(studentId);
	    return ResponseEntity.ok(averageScore);
	}
	
}



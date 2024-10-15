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

import br.com.nofrontier.sgetea.core.models.Task;
import br.com.nofrontier.sgetea.web.dtos.FlashMessage;
import br.com.nofrontier.sgetea.web.dtos.TaskDto;
import br.com.nofrontier.sgetea.web.services.TaskService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

	private TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Task task = new Task();
		model.addAttribute("task", task);
		return "task/insertTask.html";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/task/task_list");
		modelAndView.addObject("tasks", taskService.findAll());
		return modelAndView;
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/{taskId}")
	public ModelAndView findById(@PathVariable Long taskId) {
		var modelAndView = new ModelAndView("/admin/task/task_list");
		modelAndView.addObject("tasks", taskService.findById(taskId));
		return modelAndView;
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/task/task_form");
		modelAndView.addObject("taskDto", new TaskDto());
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("taskForm") TaskDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/task/task_form";
		}
		taskService.save(form);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Task registered with success!"));
		return "redirect:/admin/tasks";
	}

//---------------------------------------------------------------------------------------------------------

	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/task/task_form");
		modelAndView.addObject("taskForm", taskService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("taskForm") TaskDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/task/task_form";
		}
		taskService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Task edited with success!"));
		return "redirect:/admin/tasks";
	}

//---------------------------------------------------------------------------------------------------------

	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		taskService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Task deleted with success!"));
		return "redirect:/admin/task";
	}

}

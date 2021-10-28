package br.edu.ifpr.sgta.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import br.edu.ifpr.sgta.model.Role;
import br.edu.ifpr.sgta.repository.RoleRepository;

@Controller
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	RoleRepository daoRol;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Role rol = new Role();
		model.addAttribute("role", rol);
		return "role/inserirRole.html";
	}


	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Role rol = daoRol.findById(id).get();
		model.addAttribute("role", rol);
		return "role/inserirRole.html";
	}

	@PostMapping("/save")
	public String save(@Valid Role role, BindingResult result, Model model) {
		if (result.hasErrors())
			return "role/inserirRole.html";
		daoRol.save(role);
		return "redirect:/roles/list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		List<Role> rolList = daoRol.findAll();
		model.addAttribute("rolList", rolList);
		return "role/listRole.html";
	}

	/*@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {		
		daoRol.removeRole(id);
		return "redirect:/roles/list";
	}*/
}
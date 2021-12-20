package br.edu.ifpr.sgta.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.sgta.model.Role;
import br.edu.ifpr.sgta.repository.RoleRepository;



@RestController
@RequestMapping("/apirole")
@CrossOrigin() // desabilita CORS policy
public class ApiRoleController {
	
	@Autowired
	RoleRepository daoRol;

	@GetMapping("/permissoes")
	public List<Role> rolePerfil(@RequestParam(value = "q", required = false) String query) {
		if (!StringUtils.hasLength(query)) {
			return daoRol.findAll();
		}
		return daoRol.findByNomeIgnoreCaseContaining(query);
	}

	@GetMapping("/role/{id}")
	Optional<Role> getRole(@PathVariable Long id) {

		Role role = daoRol.findById(id).get();

		return Optional.ofNullable(role);
	}

	@GetMapping(value = "/roles")
	Iterable<Role> getRoles() {
		return daoRol.findAll();
	}

	@PostMapping("/role")
	Role postRole(@RequestBody Role role) {
		daoRol.save(role);
		return role;
	}

	@PutMapping("/role/{id}")
	ResponseEntity<Role> putRole(@PathVariable Long id, @RequestBody Role role) {
		Role r = daoRol.save(role);
		if (r != null)
			return new ResponseEntity<>(role, HttpStatus.CREATED);

		return new ResponseEntity<>(role, HttpStatus.OK);
	}

/*	@DeleteMapping("/role/{id}")
	void deleteRole(@PathVariable Long id) {
		daoRol.removeRole(id);
	}*/


}

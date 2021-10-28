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

import br.edu.ifpr.sgta.model.Usuario;
import br.edu.ifpr.sgta.repository.UsuarioRepository;


@RestController
@RequestMapping("/apiuser")
@CrossOrigin()
public class ApiUsuarioController {
	
	@Autowired
	UsuarioRepository daoUser;
	
	@GetMapping("users")
	   public List<Usuario> usuarioLista(@RequestParam(value = "q", required = false) String query) {
	       if (!StringUtils.hasLength(query)) {
	           return daoUser.findAll();
	       }
	       return daoUser.findByEmailIgnoreCaseContaining(query);
	   }	
	
	@GetMapping("/usuario/{id}")
	Optional<Usuario> getUsuario(@PathVariable Long id) {

		Usuario usuario = daoUser.findById(id).get();

		return Optional.ofNullable(usuario);
	}

	
	@GetMapping(value = "/usuarios") 
	Iterable<Usuario> getUsuarios() { 
		return daoUser.findAll();
			
	}
	
	@PostMapping("/usuario")
	Usuario postUsuario(@RequestBody Usuario usuario) {
		daoUser.save(usuario);
		return usuario;
	}
	@PutMapping("/usuario/{id}")
	ResponseEntity<Usuario> putUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		Usuario u = daoUser.save(usuario);
		if(u!=null)
			return new ResponseEntity<>(usuario, HttpStatus.CREATED);
		
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
/*  @DeleteMapping("/usuario/{id}")
  void deleteUsuario(@PathVariable Long id) {
      daoUser.removerUsuario(id);
  }*/

}

package br.com.xfrontier.sgetea.web.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.xfrontier.sgetea.core.exceptions.UserProfileNotFoundException;
import br.com.xfrontier.sgetea.core.models.Role;
import br.com.xfrontier.sgetea.core.repository.RoleRepository;
import br.com.xfrontier.sgetea.web.dtos.RoleDto;

public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
//---------------------------------------------------------------------------------------------------------

	public Role findById(Long id) {
		var roleFound = roleRepository.findById(id);
		if (roleFound.isPresent()) {
			return roleFound.get();
		}
		throw new UserProfileNotFoundException(id);
	}
	
//---------------------------------------------------------------------------------------------------------

	public Role save(RoleDto form) {
		var model = modelMapper.map(form, Role.class);
		return roleRepository.save(model);
	}
	
//---------------------------------------------------------------------------------------------------------

	public Role update(RoleDto form, Long id) {
		var roleFound = roleRepository.findById(id)
			.orElseThrow(() -> new UserProfileNotFoundException(id));
		var model = modelMapper.map(id, Role.class);
		((Role) model).setId(roleFound.getId());
		return roleRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public void deleteById(Long id) {
		var roleFound = roleRepository.findById(id)
			.orElseThrow(() -> new UserProfileNotFoundException(id));
		roleRepository.delete(roleFound);
	}

}

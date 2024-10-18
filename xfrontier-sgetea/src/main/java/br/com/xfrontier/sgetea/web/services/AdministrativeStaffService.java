package br.com.xfrontier.sgetea.web.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.xfrontier.sgetea.core.exceptions.AdministrativeStaffNotFoundException;
import br.com.xfrontier.sgetea.core.models.AdministrativeStaff;
import br.com.xfrontier.sgetea.core.repository.AdministrativeStaffRepository;
import br.com.xfrontier.sgetea.web.dtos.AdministrativeStaffDto;

public class AdministrativeStaffService {

	@Autowired
	private AdministrativeStaffRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;
	
//---------------------------------------------------------------------------------------------------------

	public List<AdministrativeStaff> findAll() {
		return employeeRepository.findAll();
	}

//---------------------------------------------------------------------------------------------------------

	public AdministrativeStaff findById(Long id) {
		var employeeFound = employeeRepository.findById(id);
		if (employeeFound.isPresent()) {
			return employeeFound.get();
		}
		throw new AdministrativeStaffNotFoundException(id);
	}

//---------------------------------------------------------------------------------------------------------

	public AdministrativeStaff save(AdministrativeStaffDto form) {
		var model = modelMapper.map(form, AdministrativeStaff.class);
		return employeeRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public AdministrativeStaff update(AdministrativeStaffDto form, Long id) {
		var employeeFound = employeeRepository.findById(id).orElseThrow(() -> new AdministrativeStaffNotFoundException(id));
		var model = modelMapper.map(id, AdministrativeStaff.class);
		((AdministrativeStaff) model).setId(employeeFound.getId());
		return employeeRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public void deleteById(Long id) {
		var employeeFound = employeeRepository.findById(id).orElseThrow(() -> new AdministrativeStaffNotFoundException(id));
		employeeRepository.delete(employeeFound);
	}

}

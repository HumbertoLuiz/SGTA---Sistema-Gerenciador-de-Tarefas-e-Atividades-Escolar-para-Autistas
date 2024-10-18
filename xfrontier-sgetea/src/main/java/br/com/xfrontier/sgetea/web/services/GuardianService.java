package br.com.xfrontier.sgetea.web.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xfrontier.sgetea.core.exceptions.GuardianNotFoundException;
import br.com.xfrontier.sgetea.core.models.Guardian;
import br.com.xfrontier.sgetea.core.repository.GuardianRepository;
import br.com.xfrontier.sgetea.web.dtos.GuardianDto;

@Service
public class GuardianService {

	@Autowired
	private GuardianRepository guardianRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Guardian> findAll() {
		return guardianRepository.findAll();
	}

//---------------------------------------------------------------------------------------------------------

	public Guardian findById(Long id) {
		var guardianFound = guardianRepository.findById(id);
		if (guardianFound.isPresent()) {
			return guardianFound.get();
		}
		throw new GuardianNotFoundException(id);
	}

//---------------------------------------------------------------------------------------------------------

	public Guardian save(GuardianDto form) {
		var model = modelMapper.map(form, Guardian.class);
		return guardianRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public Guardian update(GuardianDto form, Long id) {
		var guardianFound = guardianRepository.findById(id).orElseThrow(() -> new GuardianNotFoundException(id));
		var model = modelMapper.map(id, Guardian.class);
		((Guardian) model).setId(guardianFound.getId());
		return guardianRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public void deleteById(Long id) {
		var guardianFound = guardianRepository.findById(id).orElseThrow(() -> new GuardianNotFoundException(id));
		guardianRepository.delete(guardianFound);
	}

}

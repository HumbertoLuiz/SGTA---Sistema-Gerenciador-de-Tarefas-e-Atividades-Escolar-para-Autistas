package br.com.nofrontier.sgetea.web.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nofrontier.sgetea.core.exceptions.PhoneNotFoundException;
import br.com.nofrontier.sgetea.core.models.Phone;
import br.com.nofrontier.sgetea.core.repository.PhoneRepository;
import br.com.nofrontier.sgetea.web.dtos.PhoneDto;

@Service
public class PhoneService {

	private PhoneRepository phoneRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Phone> findAll() {
		return phoneRepository.findAll();
	}

//---------------------------------------------------------------------------------------------------------

	public Phone findById(Long id) {
		var phoneFound = phoneRepository.findById(id);
		if (phoneFound.isPresent()) {
			return phoneFound.get();
		}
		throw new PhoneNotFoundException(id);
	}

//---------------------------------------------------------------------------------------------------------

	public Phone save(PhoneDto form) {
		var model = modelMapper.map(form, Phone.class);
		return phoneRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public Phone update(PhoneDto form, Long id) {
		var phoneFound = phoneRepository.findById(id).orElseThrow(() -> new PhoneNotFoundException(id));
		var model = modelMapper.map(id, Phone.class);
		((Phone) model).setId(phoneFound.getId());
		return phoneRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public void deleteById(Long id) {
		var phoneFound = phoneRepository.findById(id).orElseThrow(() -> new PhoneNotFoundException(id));
		phoneRepository.delete(phoneFound);
	}

}

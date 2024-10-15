package br.com.nofrontier.sgetea.web.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nofrontier.sgetea.core.exceptions.AddressNotFoundException;
import br.com.nofrontier.sgetea.core.models.Address;
import br.com.nofrontier.sgetea.core.repository.AddressRepository;
import br.com.nofrontier.sgetea.web.dtos.AddressDto;

@Service
public class AddressService {

	private AddressRepository addressRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Address> findAll() {
		return addressRepository.findAll();
	}

//---------------------------------------------------------------------------------------------------------

	public Address findById(Long id) {
		var addressFound = addressRepository.findById(id);
		if (addressFound.isPresent()) {
			return addressFound.get();
		}
		throw new AddressNotFoundException(id);
	}

//---------------------------------------------------------------------------------------------------------

	public Address save(AddressDto form) {
		var model = modelMapper.map(form, Address.class);
		return addressRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public Address update(AddressDto form, Long id) {
		var addressFound = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
		var model = modelMapper.map(id, Address.class);
		((Address) model).setId(addressFound.getId());
		return addressRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public void deleteById(Long id) {
		var addressFound = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
		addressRepository.delete(addressFound);
	}

}

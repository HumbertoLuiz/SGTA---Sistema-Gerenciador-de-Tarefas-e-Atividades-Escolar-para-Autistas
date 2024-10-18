package br.com.xfrontier.sgetea.web.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xfrontier.sgetea.core.exceptions.CountryNotFoundException;
import br.com.xfrontier.sgetea.core.models.Country;
import br.com.xfrontier.sgetea.core.repository.CountryRepository;
import br.com.xfrontier.sgetea.web.dtos.CountryDto;

@Service
public class CountryService {

	private CountryRepository countryRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Country> findAll() {
		return countryRepository.findAll();
	}

//---------------------------------------------------------------------------------------------------------

	public Country findById(Long id) {
		var countryFound = countryRepository.findById(id);
		if (countryFound.isPresent()) {
			return countryFound.get();
		}
		throw new CountryNotFoundException(id);
	}

//---------------------------------------------------------------------------------------------------------

	public Country save(CountryDto form) {
		var model = modelMapper.map(form, Country.class);
		return countryRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public Country update(CountryDto form, Long id) {
		var countryFound = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
		var model = modelMapper.map(id, Country.class);
		((Country) model).setId(countryFound.getId());
		return countryRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public void deleteById(Long id) {
		var countryFound = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
		countryRepository.delete(countryFound);
	}

}

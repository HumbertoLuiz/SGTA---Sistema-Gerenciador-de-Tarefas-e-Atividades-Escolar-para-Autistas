package br.com.nofrontier.sgetea.web.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nofrontier.sgetea.core.exceptions.CityNotFoundException;
import br.com.nofrontier.sgetea.core.models.City;
import br.com.nofrontier.sgetea.core.repository.CityRepository;
import br.com.nofrontier.sgetea.web.dtos.CityDto;

@Service
public class CityService {

	private CityRepository cityRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<City> findAll() {
		return cityRepository.findAll();
	}

//---------------------------------------------------------------------------------------------------------

	public City findById(Long id) {
		var cityFound = cityRepository.findById(id);
		if (cityFound.isPresent()) {
			return cityFound.get();
		}
		throw new CityNotFoundException(id);
	}

//---------------------------------------------------------------------------------------------------------

	public City save(CityDto form) {
		var model = modelMapper.map(form, City.class);
		return cityRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public City update(CityDto form, Long id) {
		var cityFound = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
		var model = modelMapper.map(id, City.class);
		((City) model).setId(cityFound.getId());
		return cityRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public void deleteById(Long id) {
		var cityFound = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
		cityRepository.delete(cityFound);
	}

}

package br.com.xfrontier.sgetea.web.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xfrontier.sgetea.core.exceptions.StateNotFoundException;
import br.com.xfrontier.sgetea.core.models.State;
import br.com.xfrontier.sgetea.core.repository.StateRepository;
import br.com.xfrontier.sgetea.web.dtos.StateDto;

@Service
public class StateService {

	private StateRepository stateRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<State> findAll() {
		return stateRepository.findAll();
	}

//---------------------------------------------------------------------------------------------------------

	public State findById(Long id) {
		var stateFound = stateRepository.findById(id);
		if (stateFound.isPresent()) {
			return stateFound.get();
		}
		throw new StateNotFoundException(id);
	}

//---------------------------------------------------------------------------------------------------------

	public State save(StateDto form) {
		var model = modelMapper.map(form, State.class);
		return stateRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public State update(StateDto form, Long id) {
		var stateFound = stateRepository.findById(id).orElseThrow(() -> new StateNotFoundException(id));
		var model = modelMapper.map(id, State.class);
		((State) model).setId(stateFound.getId());
		return stateRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public void deleteById(Long id) {
		var stateFound = stateRepository.findById(id).orElseThrow(() -> new StateNotFoundException(id));
		stateRepository.delete(stateFound);
	}

}

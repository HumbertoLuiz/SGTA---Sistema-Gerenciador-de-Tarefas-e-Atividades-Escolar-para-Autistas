package br.com.nofrontier.sgetea.web.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nofrontier.sgetea.core.exceptions.SubjectNotFoundException;
import br.com.nofrontier.sgetea.core.models.Subject;
import br.com.nofrontier.sgetea.core.repository.SubjectRepository;
import br.com.nofrontier.sgetea.web.dtos.SubjectDto;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectRepository disciplineRepository;

	@Autowired
	private ModelMapper modelneMapper;

	public List<Subject> findAll() {
		return disciplineRepository.findAll();
	}
	
//---------------------------------------------------------------------------------------------------------

	public Subject findById(Long id) {
		var disciplineFound = disciplineRepository.findById(id);
		if (disciplineFound.isPresent()) {
			return disciplineFound.get();
		}
		throw new SubjectNotFoundException(id);
	}
	
//---------------------------------------------------------------------------------------------------------

	public Subject save(SubjectDto form) {
		var model = modelneMapper.map(form, Subject.class);
		return disciplineRepository.save(model);
	}
	
//---------------------------------------------------------------------------------------------------------

	public Subject update(SubjectDto form, Long id) {
		var disciplineFound = disciplineRepository.findById(id)
			.orElseThrow(() -> new SubjectNotFoundException(id));
		var model = modelneMapper.map(id, Subject.class);
		((Subject) model).setId(disciplineFound.getId());
		return disciplineRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public void deleteById(Long id) {
		var disciplineFound = disciplineRepository.findById(id)
			.orElseThrow(() -> new SubjectNotFoundException(id));
		disciplineRepository.delete(disciplineFound);
	}

}

package br.com.xfrontier.sgetea.web.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xfrontier.sgetea.core.exceptions.ClassGroupNotFoundException;
import br.com.xfrontier.sgetea.core.models.ClassGroup;
import br.com.xfrontier.sgetea.core.repository.ClassGroupRepository;
import br.com.xfrontier.sgetea.web.dtos.ClassGroupDto;

@Service
public class ClassGroupService {

	private ClassGroupRepository courseClassRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<ClassGroup> findAll() {
		return courseClassRepository.findAll();
	}

//---------------------------------------------------------------------------------------------------------

	public ClassGroup findById(Long id) {
		var courseClassFound = courseClassRepository.findById(id);
		if (courseClassFound.isPresent()) {
			return courseClassFound.get();
		}
		throw new ClassGroupNotFoundException(id);
	}

//---------------------------------------------------------------------------------------------------------

	public ClassGroup save(ClassGroupDto form) {
		var model = modelMapper.map(form, ClassGroup.class);
		return courseClassRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public ClassGroup update(ClassGroupDto form, Long id) {
		var courseClassFound = courseClassRepository.findById(id).orElseThrow(() -> new ClassGroupNotFoundException(id));
		var model = modelMapper.map(id, ClassGroup.class);
		((ClassGroup) model).setId(courseClassFound.getId());
		return courseClassRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public void deleteById(Long id) {
		var courseClassFound = courseClassRepository.findById(id).orElseThrow(() -> new ClassGroupNotFoundException(id));
		courseClassRepository.delete(courseClassFound);
	}

}

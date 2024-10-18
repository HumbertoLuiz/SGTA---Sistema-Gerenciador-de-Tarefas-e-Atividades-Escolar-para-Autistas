package br.com.xfrontier.sgetea.web.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.xfrontier.sgetea.core.exceptions.TeacherNotFoundException;
import br.com.xfrontier.sgetea.core.models.Teacher;
import br.com.xfrontier.sgetea.core.repository.TeacherRepository;
import br.com.xfrontier.sgetea.web.dtos.TeacherDto;

public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Teacher> findAll() {
		return teacherRepository.findAll();
	}
	
//---------------------------------------------------------------------------------------------------------

	public Teacher findById(Long id) {
		var teacherFound = teacherRepository.findById(id);
		if (teacherFound.isPresent()) {
			return teacherFound.get();
		}
		throw new TeacherNotFoundException(id);
	}
	
//---------------------------------------------------------------------------------------------------------

	public Teacher save(TeacherDto form) {
		var model = modelMapper.map(form, Teacher.class);
		return teacherRepository.save(model);
	}
	
//---------------------------------------------------------------------------------------------------------

	public Teacher update(TeacherDto form, Long id) {
		var teacherFound = teacherRepository.findById(id)
			.orElseThrow(() -> new TeacherNotFoundException(id));
		var model = modelMapper.map(id, Teacher.class);
		((Teacher) model).setId(teacherFound.getId());
		return teacherRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public void deleteById(Long id) {
		var teacherFound = teacherRepository.findById(id)
			.orElseThrow(() -> new TeacherNotFoundException(id));
		teacherRepository.delete(teacherFound);
	}

}

package br.com.xfrontier.sgetea.web.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xfrontier.sgetea.core.exceptions.CourseNotFoundException;
import br.com.xfrontier.sgetea.core.models.Course;
import br.com.xfrontier.sgetea.core.repository.CourseRepository;
import br.com.xfrontier.sgetea.web.dtos.CourseDto;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

//---------------------------------------------------------------------------------------------------------

	public Course findById(Long id) {
		var courseFound = courseRepository.findById(id);
		if (courseFound.isPresent()) {
			return courseFound.get();
		}
		throw new CourseNotFoundException(id);
	}

//---------------------------------------------------------------------------------------------------------

	public Course save(CourseDto form) {
		var model = modelMapper.map(form, Course.class);
		return courseRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public Course update(CourseDto form, Long id) {
		var courseFound = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
		var model = modelMapper.map(id, Course.class);
		((Course) model).setId(courseFound.getId());
		return courseRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public void deleteById(Long id) {
		var courseFound = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
		courseRepository.delete(courseFound);
	}

}

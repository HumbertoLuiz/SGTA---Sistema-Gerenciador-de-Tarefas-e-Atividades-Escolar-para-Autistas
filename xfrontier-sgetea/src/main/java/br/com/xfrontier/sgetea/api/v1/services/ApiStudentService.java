package br.com.xfrontier.sgetea.api.v1.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.xfrontier.sgetea.core.exceptions.StudentNotFoundException;
import br.com.xfrontier.sgetea.core.models.Grade;
import br.com.xfrontier.sgetea.core.models.Student;
import br.com.xfrontier.sgetea.core.repository.GradeRepository;
import br.com.xfrontier.sgetea.core.repository.StudentRepository;
import br.com.xfrontier.sgetea.web.dtos.StudentDto;

public class ApiStudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Student> findAll() {
		return studentRepository.findAll();
	}
	
//---------------------------------------------------------------------------------------------------------

	public Student findById(Long id) {
		var studentFound = studentRepository.findById(id);
		if (studentFound.isPresent()) {
			return studentFound.get();
		}
		throw new StudentNotFoundException(id);
	}
	
//---------------------------------------------------------------------------------------------------------

	public Student save(StudentDto form) {
		var model = modelMapper.map(form, Student.class);
		return studentRepository.save(model);
	}
	
//---------------------------------------------------------------------------------------------------------

	public Student update(StudentDto form, Long id) {
		var studentFound = studentRepository.findById(id)
			.orElseThrow(() -> new StudentNotFoundException(id));
		var model = modelMapper.map(id, Student.class);
		((Student) model).setId(studentFound.getId());
		return studentRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public void deleteById(Long id) {
		var studentFound = studentRepository.findById(id)
			.orElseThrow(() -> new StudentNotFoundException(id));
		studentRepository.delete(studentFound);
	}
	
	public Double calculateAverageScoreForStudent(Long studentId) {
	    studentRepository.findById(studentId)
	            .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + studentId));

	    List<Grade> grades = gradeRepository.findByStudentId(studentId);
	    return grades.stream()
	                 .mapToDouble(Grade::getScore)
	                 .average()
	                 .orElse(0.0);
	}


}

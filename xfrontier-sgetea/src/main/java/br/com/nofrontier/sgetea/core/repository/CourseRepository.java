package br.com.nofrontier.sgetea.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nofrontier.sgetea.core.models.Course;


public interface CourseRepository extends JpaRepository<Course, Long>{

}

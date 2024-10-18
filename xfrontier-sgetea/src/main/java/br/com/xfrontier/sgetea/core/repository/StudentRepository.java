package br.com.xfrontier.sgetea.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xfrontier.sgetea.core.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

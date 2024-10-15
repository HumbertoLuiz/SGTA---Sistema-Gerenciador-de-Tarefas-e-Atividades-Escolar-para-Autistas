package br.com.nofrontier.sgetea.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nofrontier.sgetea.core.models.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {

	List<Grade> findByStudentId(Long studentId);
}

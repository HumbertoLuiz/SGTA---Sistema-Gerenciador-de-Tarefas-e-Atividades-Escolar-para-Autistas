package br.com.xfrontier.sgetea.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xfrontier.sgetea.core.models.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {

	List<Grade> findByStudentId(Long studentId);
}

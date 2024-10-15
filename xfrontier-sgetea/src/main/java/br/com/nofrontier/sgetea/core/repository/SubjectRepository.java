package br.com.nofrontier.sgetea.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nofrontier.sgetea.core.models.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

	List<Subject> findClassById(Long id);

}

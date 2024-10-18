package br.com.xfrontier.sgetea.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xfrontier.sgetea.core.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}

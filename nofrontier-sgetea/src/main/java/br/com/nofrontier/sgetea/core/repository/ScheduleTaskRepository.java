package br.com.nofrontier.sgetea.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nofrontier.sgetea.core.models.Task;

public interface ScheduleTaskRepository extends JpaRepository<Task, Long> {

}

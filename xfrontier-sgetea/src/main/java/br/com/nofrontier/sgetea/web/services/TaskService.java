package br.com.nofrontier.sgetea.web.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.nofrontier.sgetea.core.exceptions.TaskNotFoundException;
import br.com.nofrontier.sgetea.core.models.Task;
import br.com.nofrontier.sgetea.core.repository.TaskRepository;
import br.com.nofrontier.sgetea.web.dtos.TaskDto;

public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Task> findAll() {
		return taskRepository.findAll();
	}
	
//---------------------------------------------------------------------------------------------------------

	public Task findById(Long id) {
		var taskFound = taskRepository.findById(id);
		if (taskFound.isPresent()) {
			return taskFound.get();
		}
		throw new TaskNotFoundException(id);
	}
	
//---------------------------------------------------------------------------------------------------------

	public Task save(TaskDto form) {
		var model = modelMapper.map(form, Task.class);
		return taskRepository.save(model);
	}
	
//---------------------------------------------------------------------------------------------------------

	public Task update(TaskDto form, Long id) {
		var taskFound = taskRepository.findById(id)
			.orElseThrow(() -> new TaskNotFoundException(id));
		var model = modelMapper.map(id, Task.class);
		((Task) model).setId(taskFound.getId());
		return taskRepository.save(model);
	}

//---------------------------------------------------------------------------------------------------------

	public void deleteById(Long id) {
		var taskFound = taskRepository.findById(id)
			.orElseThrow(() -> new TaskNotFoundException(id));
		taskRepository.delete(taskFound);
	}
}

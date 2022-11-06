package com.example.tasksmanagementsystem.service;

import com.example.tasksmanagementsystem.dto.TaskUpdateDto;
import com.example.tasksmanagementsystem.exception.DuplicateEntityException;
import com.example.tasksmanagementsystem.model.Task;
import com.example.tasksmanagementsystem.repository.StudentRepository;
import com.example.tasksmanagementsystem.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService implements TaskInterface {
	private final TaskRepository taskRepository;
	private final StudentRepository studentRepository;

	@Override
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task getTaskById(Long taskId) {
		var task = this.taskRepository.findTaskById(taskId);
		if (task.isEmpty()) {
			throw new EntityNotFoundException("The task with the specified id was not found!");
		}
		return task.get();
	}

	@Override
	public Long createTask(Task task) {
		var titleOptional = taskRepository
			.findTaskByTitle(task.getTitle());
		if(titleOptional.isPresent()) {
			throw new DuplicateEntityException("User with this title already exists.");
		}
		taskRepository.save(task);
		return task.getId();
	}

	@Override
	public void deleteTask(Long taskId) {
		boolean taskExists = taskRepository.existsById(taskId);
		if (!taskExists) {
			throw new EntityNotFoundException("Task by this id: " + taskId + " does not exist.");
		}
		taskRepository.deleteById(taskId);
		log.warn("Task with id {} , has been deleted.", taskId);
	}

	@Override
	@Transactional
	public void updateTask(Long taskId, TaskUpdateDto updatedTask) {
		var taskFromDb = taskRepository.findById(taskId)
			.orElseThrow(() -> new EntityNotFoundException(
				"task id " + taskId + "does not exists."
			));
		taskFromDb.setTitle(updatedTask.getTitle());
		taskFromDb.setDescription(updatedTask.getDescription());

	}

	@Override
	@Transactional
	public Task assignTask(Long taskId, Long studentId) {
		var task = taskRepository.findTaskById(taskId).get();
		var student = studentRepository.findById(studentId).get();
		task.assignStudent(student);
		return task;
	}


}

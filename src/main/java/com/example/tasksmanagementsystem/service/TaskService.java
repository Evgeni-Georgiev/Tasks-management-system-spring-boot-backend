package com.example.tasksmanagementsystem.service;

import com.example.tasksmanagementsystem.dto.TaskUpdateDto;
import com.example.tasksmanagementsystem.model.Task;
import com.example.tasksmanagementsystem.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
	private final TaskRepository taskRepository;

	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	public Optional<Task> getTaskById(Long taskId) {
		return taskRepository.findTaskById(taskId);
	}

	public Optional<Task> getTaskByStudentId(Long studentId) {
		return taskRepository.findTaskByStudentId(studentId);
	}

	public void createTask(Task task) {
		Optional<Task> taskOptional = taskRepository
			.findTaskByTitle(task.getTitle());
		if(taskOptional.isPresent()) {
			throw new IllegalStateException("Title is taken");
		}
		taskRepository.save(task);
	}

	public void deleteTask(Long taskId) {
		boolean taskExists = taskRepository.existsById(taskId);
		if(!taskExists) {
			throw new IllegalStateException("Task by this id: " + taskId + " does not exist.");
		}
		taskRepository.deleteById(taskId);
	}

	@Transactional
	public void updateTask(Long taskId, TaskUpdateDto updatedTask) {
		var taskFromDb = taskRepository.findById(taskId)
			.orElseThrow(() -> new IllegalStateException(
				"task id " + taskId + "does not exists."
			));
		taskFromDb.setTitle(updatedTask.getTitle());
		taskFromDb.setDescription(updatedTask.getDescription());

//		return taskRepository.findById(taskId)
//			.map(singleTask -> {
//				singleTask.setTitle(task.getTitle());
//				singleTask.setDescription(task.getDescription());
//				return taskRepository.save(singleTask);
//			});
	}
}

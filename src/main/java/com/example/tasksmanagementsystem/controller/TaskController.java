package com.example.tasksmanagementsystem.controller;

import com.example.tasksmanagementsystem.dto.TaskUpdateDto;
import com.example.tasksmanagementsystem.model.Student;
import com.example.tasksmanagementsystem.model.Task;
import com.example.tasksmanagementsystem.repository.StudentRepository;
import com.example.tasksmanagementsystem.repository.TaskRepository;
import com.example.tasksmanagementsystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/v1/task")
public class TaskController {

	private final TaskService taskService;
	@Autowired
	private final TaskRepository taskRepository;
	@Autowired
	private final StudentRepository studentRepository;

	@Autowired
	public TaskController(TaskService taskService, TaskRepository taskRepository, StudentRepository studentRepository) {
		this.taskService = taskService;
		this.taskRepository = taskRepository;
		this.studentRepository = studentRepository;
	}

	@GetMapping
	public List<Task> getTask() {
		return taskService.getTasks();
	}

	@GetMapping(path = "student/{studentId}")
	public Optional<Task> getTaskByStudentId(@PathVariable Long studentId) {
		return taskService.getTaskByStudentId(studentId);
	}

	@GetMapping(path = "{taskId}")
	public Optional<Task> getTaskById(@PathVariable Long taskId) {
		return taskService.getTaskById(taskId);
	}

	@PostMapping
	public void createNewTask(@RequestBody Task task) {
		taskService.createTask(task);
	}

	@DeleteMapping(path = "{taskId}")
	public void deleteTask(@PathVariable("taskId") Long taskId) {
		taskService.deleteTask(taskId);
	}

	@PutMapping(path = "{taskId}")
	public ResponseEntity<?> updateTask(
		@PathVariable("taskId") Long taskId,
		@RequestBody TaskUpdateDto taskUpdatedDto
	) {
		taskService.updateTask(taskId, taskUpdatedDto);
		return ResponseEntity.noContent().build();
	}


	@PutMapping(path = "{taskId}/student/{studentId}")
	public Task assignTaskToStudent(
		@PathVariable Long taskId,
		@PathVariable Long studentId
	) {
		Task task = taskRepository.findById(taskId).get();
		Student student = studentRepository.findById(studentId).get();
		task.assignStudent(student);
		return taskRepository.save(task);
	}

}

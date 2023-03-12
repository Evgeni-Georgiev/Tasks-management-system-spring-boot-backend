package com.example.tasksmanagementsystem.controller;

import com.example.tasksmanagementsystem.dto.TaskUpdateDto;
import com.example.tasksmanagementsystem.model.Task;
import com.example.tasksmanagementsystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080/", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/task")
public class TaskController {

	private final TaskService taskService;

	@GetMapping
	public List<Task> getTask() {
		return taskService.getTasks();
	}

	@GetMapping(path = "{taskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
		var task = taskService.getTaskById(taskId);
		return ResponseEntity.ok().body(task);
	}

	@PostMapping
	public ResponseEntity<?> createNewTask(@RequestBody Task task) {
		var newTask = taskService.createTask(task);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/task/" + newTask).toUriString());
		return ResponseEntity.ok().body(uri);
	}

	@DeleteMapping(path = "{taskId}")
	public ResponseEntity<?> deleteTask(@PathVariable("taskId") Long taskId) {
		taskService.deleteTask(taskId);
		return ResponseEntity.noContent().build();
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
	public ResponseEntity<?> assignTaskToStudent(
		@PathVariable Long taskId,
		@PathVariable Long studentId
	) {
		taskService.assignTask(taskId, studentId);
		return ResponseEntity.noContent().build();
	}

}

package com.example.tasksmanagementsystem.controller;

import com.example.tasksmanagementsystem.model.Student;
import com.example.tasksmanagementsystem.model.Task;
import com.example.tasksmanagementsystem.repository.StudentRepository;
import com.example.tasksmanagementsystem.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AssignStudentToTaskController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TaskRepository taskRepository;

//	@PostMapping("/student/{studentId}/task/{taskId}")
//	public void assignStudentToTask(@PathVariable("studentId") Long studentId, @PathVariable("taskId") Long taskId) {
//		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Invalid student id"));
//		Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Invalid task id"));
//		task.setStudent(student);
//		student.getTasks().add(task);
//		studentRepository.save(student);
//	}

	@PutMapping("/student/{studentId}/task/{taskId}")
	public void assignStudentToTask(@PathVariable("studentId") Long studentId, @PathVariable("taskId") Long taskId) {
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Invalid student id"));
		Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Invalid task id"));
		task.setStudent(student);
		student.getTasks().add(task);
		studentRepository.save(student);
	}

}

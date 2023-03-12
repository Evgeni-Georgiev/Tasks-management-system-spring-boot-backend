package com.example.tasksmanagementsystem.controller;

import com.example.tasksmanagementsystem.dto.StudentUpdateDto;
import com.example.tasksmanagementsystem.model.Student;
import com.example.tasksmanagementsystem.repository.StudentRepository;
import com.example.tasksmanagementsystem.repository.TaskRepository;
import com.example.tasksmanagementsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins="http://localhost:8080", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/v1/student")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	private final StudentRepository studentRepository;

	@Autowired
	private final TaskRepository taskRepository;

	@GetMapping
	public ResponseEntity<List<Student>> getStudents() {
		var studentsList = studentService.getStudents();
		return ResponseEntity.ok().body(studentsList);
	}

	@GetMapping(path="{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
		var student = studentService.getStudentById(studentId);
		return ResponseEntity.ok().body(student);
	}

	@PostMapping
	public ResponseEntity<?> registerNewStudent(@RequestBody Student student) {
		Long newStudent = studentService.addNewStudent(student);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/city/" + newStudent).toUriString());
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("{studentId}")
	public ResponseEntity<?> updateStudent(@PathVariable Long studentId, @RequestBody StudentUpdateDto studentUpdateDto) {
		studentService.updateStudent(studentId, studentUpdateDto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path="{studentId}")
	public ResponseEntity<?> deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
		return ResponseEntity.noContent().build();
	}

//	@PutMapping("/student/{studentId}/task/{taskId}")
//	public void assignStudentToTask(@PathVariable("studentId") Long studentId, @PathVariable("taskId") Long taskId) {
//		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Invalid student id"));
//		Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Invalid task id"));
//		task.setStudent(student);
//		student.getTasks().add(task);
//		studentRepository.save(student);
//	}
}

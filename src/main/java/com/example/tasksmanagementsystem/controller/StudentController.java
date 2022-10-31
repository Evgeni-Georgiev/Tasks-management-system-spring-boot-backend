package com.example.tasksmanagementsystem.controller;

import com.example.tasksmanagementsystem.model.Student;
import com.example.tasksmanagementsystem.repository.StudentRepository;
import com.example.tasksmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins="http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {
	private final StudentService studentService;
	private final StudentRepository studentRepository;

	@Autowired
	public StudentController(StudentService studentService, StudentRepository studentRepository) {
		this.studentService = studentService;
		this.studentRepository = studentRepository;
	}

	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

	@GetMapping(path="{studentId}")
	public Optional<Student> getStudentById(@PathVariable Long studentId) {
		return studentService.getStudentById(studentId);
	}

	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}

//	@PostMapping
//	public Student registerNewStudent(@RequestBody Student student) {
//		return studentRepository.save(student);
//	}

	@DeleteMapping(path="{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
	}

//	@PutMapping(path="{studentId}")
//	public void updateStudent(
//		@PathVariable("studentId") Long studentId,
//									Student student,
//		@RequestParam(required = false) String name,
//		@RequestParam(required = false) String email,
//		@RequestParam(required = false) String picture,
//		@RequestParam(required = false) Long phone,
//		@RequestParam(required = false) String address
//		) {
//		studentService.updateStudent(studentId, student, name, email, picture, phone, address);
//	}



	@PutMapping(path="{studentId}")
	public Optional<Student> updateStudent(
		@PathVariable("studentId") Long studentId,
		@RequestBody Student student,
		@RequestParam(required = false) String name,
		@RequestParam(required = false) String email,
		@RequestParam(required = false) Long phone,
		@RequestParam(required = false) String address,
		@RequestParam(required = false) String role,
		@RequestParam(required = false) Boolean isActive,
		@RequestParam(required = false) String picture,
		@RequestParam(required = false) String password
	) {
		return studentRepository.findById(studentId)
			.map(singleStudent -> {
				singleStudent.setName(student.getName());
				singleStudent.setEmail(student.getEmail());
				singleStudent.setPicture(student.getPicture());
				singleStudent.setPhone(student.getPhone());
				singleStudent.setAddress(student.getAddress());
				return studentRepository.save(singleStudent);
			});
	}


}

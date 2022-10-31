package com.example.tasksmanagementsystem.service;

import com.example.tasksmanagementsystem.model.Student;
import com.example.tasksmanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
// Here is the business logic
@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		// instead of returning a list, do:
		return studentRepository.findAll();
	}

	public Optional<Student> getStudentById(Long studentId) {
		return studentRepository.findStudentById(studentId);
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository
			.findStudentByEmail(student.getEmail());
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("Email is taken");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException(
				"student with id " + studentId + " does not exists"
				);
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(Long studentId,
							  String name,
							  String email,
							  Long phone,
							  String address,
							  String picture
							  ) {
		Student student = studentRepository.findById(studentId)
			.orElseThrow(() -> new IllegalStateException(
				"student width id " + studentId + "does not exists."
			));
		if(name != null
			&& name.length() > 0
			&& !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}

		Optional<Student> studentOptional = studentRepository
			.findStudentByEmail(email);
		if(email != null
			&& email.length() > 0
			&& !Objects.equals(student.getEmail(), email)) {
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			student.setEmail(email);
		}
		student.setPhone(phone);
		student.setAddress(address);
		student.setPicture(picture);

	}
}

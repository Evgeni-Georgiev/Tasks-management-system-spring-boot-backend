package com.example.tasksmanagementsystem.service;

import com.example.tasksmanagementsystem.dto.StudentUpdateDto;
import com.example.tasksmanagementsystem.exception.DuplicateEntityException;
import com.example.tasksmanagementsystem.exception.ResourceNotFoundException;
import com.example.tasksmanagementsystem.model.Student;
import com.example.tasksmanagementsystem.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
@Slf4j
public class StudentService implements StudentInterface {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(Long studentId) {
		return studentRepository.findStudentById(studentId);
	}

	@Override
	public Long addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository
			.findStudentByEmail(student.getEmail());
		if(studentOptional.isPresent()) {
			throw new DuplicateEntityException("User with this email already exists.");
		}
		studentRepository.save(student);
		return student.getId();
	}

	@Override
	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if(!exists) {
			throw new ResourceNotFoundException(
				"Student with id " + studentId + " does not exists"
				);
		}
		studentRepository.deleteById(studentId);
	}

	@Override
	@Transactional
	public void updateStudent(Long studentId, StudentUpdateDto studentUpdateDto) {
		Student student = studentRepository.findById(studentId)
			.orElseThrow(() -> new ResourceNotFoundException(
				"Student width id " + studentId + " does not exists."
			));
		if(!studentUpdateDto.getEmail().isEmpty() && !studentUpdateDto.getEmail().isBlank()) {
			if (this.studentRepository.existsByEmail(studentUpdateDto.getEmail()))
				throw new DuplicateEntityException("Student with that email already exists.");
			else {
				student.setName(studentUpdateDto.getName());
				student.setEmail(studentUpdateDto.getEmail());
				student.setPhone(studentUpdateDto.getPhone());
				student.setAddress(studentUpdateDto.getAddress());
				student.setPicture(studentUpdateDto.getPicture());
				log.info("Student with id {} has been updated.", studentId);
			}
		}

	}
}

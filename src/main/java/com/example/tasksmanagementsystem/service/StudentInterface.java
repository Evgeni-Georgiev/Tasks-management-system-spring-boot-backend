package com.example.tasksmanagementsystem.service;

import com.example.tasksmanagementsystem.dto.StudentUpdateDto;
import com.example.tasksmanagementsystem.model.Student;

import java.util.List;

public interface StudentInterface {
	List<Student> getStudents();
	Student getStudentById(Long studentId);
	Long addNewStudent(Student student);
	void deleteStudent(Long studentId);

	void updateStudent(Long studentId, StudentUpdateDto studentUpdateDto);
}

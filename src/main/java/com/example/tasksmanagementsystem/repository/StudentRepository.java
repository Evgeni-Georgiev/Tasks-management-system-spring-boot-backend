package com.example.tasksmanagementsystem.repository;

import com.example.tasksmanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	// Will transform into SQL: SELECT * FROM student WHERE email = ?
//	 @Query("SELECT s FROM Student s WHERE s.email = ?1") //- this is jpql
	Optional<Student> findStudentByEmail(String email);

	Student findStudentById(Long id);

	Boolean existsByEmail(String email);

}

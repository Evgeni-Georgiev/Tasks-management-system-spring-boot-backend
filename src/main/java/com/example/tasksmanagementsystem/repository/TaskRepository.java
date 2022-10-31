package com.example.tasksmanagementsystem.repository;

import com.example.tasksmanagementsystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	Optional<Task> findTaskById(Long id);
//	@Query("SELECT t FROM Task t INNER JOIN Student s WHERE s.id=?1")
	Optional<Task> findTaskByStudentId(Long studentId);
	Optional<Task> findTaskByTitle(String title);

}

package com.example.tasksmanagementsystem.repository;

import com.example.tasksmanagementsystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	Optional<Task> findTaskById(Long id);
	Optional<Task> findTaskByTitle(String title);
}

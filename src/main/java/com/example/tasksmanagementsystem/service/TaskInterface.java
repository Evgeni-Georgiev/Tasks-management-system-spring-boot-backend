package com.example.tasksmanagementsystem.service;

import com.example.tasksmanagementsystem.dto.TaskUpdateDto;
import com.example.tasksmanagementsystem.model.Task;

import javax.transaction.Transactional;
import java.util.List;

public interface TaskInterface {
	List<Task> getTasks();
	Task getTaskById(Long taskId);
	Long createTask(Task task);
	void deleteTask(Long taskId);
	void updateTask(Long taskId, TaskUpdateDto updatedTask);

	@Transactional
	Task assignTask(Long taskId, Long studentId);
}

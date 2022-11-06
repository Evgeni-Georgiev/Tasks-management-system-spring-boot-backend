package com.example.tasksmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Task {

	@Id
	@SequenceGenerator(
		name="task_sequence",
		sequenceName = "task_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE
	)
	private Long id;
	private String title;
	private String description;

	@ManyToOne
	@JoinColumn(name="student_id", referencedColumnName = "id")
	private Student student;

	public Task(String title, String description, Student student) {
		this.title = title;
		this.description = description;
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Student getStudent() {
		return student;
	}

	public void assignStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Task{" +
			"id=" + id +
			", title='" + title + '\'' +
			", description='" + description + '\'' +
			", student='" + student + '\'' +
			'}';
	}
}

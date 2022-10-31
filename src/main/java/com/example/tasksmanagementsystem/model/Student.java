package com.example.tasksmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Model
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	// Mapped Student class to a table in db
	@Id
	@SequenceGenerator(
		name = "student_sequence",
		sequenceName = "student_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE
	)
	private Long id;

	private String name;
	private String email;
	private Long phone;
	private String address;
	private String picture;

	@Transient

	@JsonIgnore
//	@OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE, orphanRemoval = true)
//	@OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
	@OneToMany(mappedBy = "student")
	private Set<Task> tasks = new HashSet<>();

	public Student(String name,
				   String email,
				   Long phone,
				   String address,
				   String picture) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.picture = picture;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	@Override
	public String toString() {
		return "Student{" +
			"id=" + id +
			", name='" + name + '\'' +
			", email='" + email + '\'' +
			", phone=" + phone +
			", address='" + address + '\'' +
			", picture='" + picture + '\'' +
			'}';
	}
}

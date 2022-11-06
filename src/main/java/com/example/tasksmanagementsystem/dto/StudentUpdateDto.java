package com.example.tasksmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateDto {
	@NonNull
	private String name;

	@NonNull
	private String email;

	@NonNull
	private Long phone;

	@NonNull
	private String address;

	@NonNull
	private String picture;
}

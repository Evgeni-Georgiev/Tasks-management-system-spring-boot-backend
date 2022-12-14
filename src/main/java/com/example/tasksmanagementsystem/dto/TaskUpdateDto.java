package com.example.tasksmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateDto {

	@NonNull
	private String title;

	@NonNull
	private String description;

	@NonNull
	private Long studentId;
}

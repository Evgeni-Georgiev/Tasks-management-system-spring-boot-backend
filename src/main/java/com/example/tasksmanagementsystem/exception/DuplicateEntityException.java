package com.example.tasksmanagementsystem.exception;

public class DuplicateEntityException extends RuntimeException {
	public DuplicateEntityException(String message) {
		super(message);
	}
}

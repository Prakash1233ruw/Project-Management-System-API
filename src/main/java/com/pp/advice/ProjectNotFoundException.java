package com.pp.advice;

public class ProjectNotFoundException extends RuntimeException {

	public ProjectNotFoundException(String message) {
		super(message);
	}
}

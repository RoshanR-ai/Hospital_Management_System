package com.cts.exception;

public class DoctorNotFoundException extends RuntimeException {
	public DoctorNotFoundException(String message) {
		super(message);
	}
}

package com.mike.nasa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NasaControllerAdvice {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleException(IllegalArgumentException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Unable to find photos for one of the dates");
	}
}

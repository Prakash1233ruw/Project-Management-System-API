package com.pp.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ProjectErrorController {
     @ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<?> handleExceptionForTourist(ProjectNotFoundException e){
    	 System.out.println("Advice ProjectExceptionHandler");
		ErrorDetails details=new ErrorDetails("404 Not Found", e.getMessage(), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(details,HttpStatus.BAD_REQUEST);
	}
     
     @ExceptionHandler(Exception.class)
 	public ResponseEntity<?> handleExceptionGlobally(Exception e){
     	 System.out.println("Advice ExceptionHandler");
 		ErrorDetails details=new ErrorDetails("404 Not Found", e.getMessage(), LocalDateTime.now());
 		return new ResponseEntity<ErrorDetails>(details,HttpStatus.INTERNAL_SERVER_ERROR);
 	}
}

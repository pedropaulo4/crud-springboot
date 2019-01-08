package com.pedropaulo.cursomc.resources.excepetion;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pedropaulo.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class) // pra indicar que é um tratador de exceções
	public ResponseEntity<ErroPadrao> objectNotFound(ObjectNotFoundException e
			, HttpServletRequest request) {
		
		ErroPadrao err = new ErroPadrao(HttpStatus.NOT_FOUND.value(), 
				e.getMessage(), System.currentTimeMillis());
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
		
		
	}

}

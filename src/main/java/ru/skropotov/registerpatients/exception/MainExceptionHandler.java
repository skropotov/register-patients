package ru.skropotov.registerpatients.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ru.skropotov.registerpatients.models.dto.ExceptionDto;

@ControllerAdvice 
public class MainExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ExceptionDto handleEntityNotFound(EntityNotFoundException e) {
		return new ExceptionDto(e.getMessage());
	} 
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionDto handleRuntimeException(RuntimeException e) {
		return new ExceptionDto(e.getMessage());
	} 
	
}

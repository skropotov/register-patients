package ru.skropotov.registerpatients.models.dto;

public class ExceptionDto {
	private String message;

	public ExceptionDto(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

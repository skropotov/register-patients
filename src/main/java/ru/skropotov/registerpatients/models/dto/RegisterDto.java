package ru.skropotov.registerpatients.models.dto;

public class RegisterDto {
	private Long ticketId;
	private Long patientId;
	
	public RegisterDto() {}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	
}

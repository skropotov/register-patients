package ru.skropotov.registerpatients.endpoints;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import ru.skropotov.registerpatients.services.TicketService;
import ru.skropotov.ws.ticket_service.CreateTicketsRequest;

@Endpoint
public class TicketEndpoint {
	private static final String NAMESPACE_URI = "http://skropotov.ru/ws/ticket-service"; 
	
	@Autowired
	TicketService ticketService;
	
	private Logger log = LoggerFactory.getLogger(TicketEndpoint.class);
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createTicketsRequest") 
	public void createTickets(@RequestPayload CreateTicketsRequest request) {
		log.info("startHour=" + String.valueOf(request.getStartHour()) + " endHour=" + String.valueOf(request.getEndHour()) + 
				" startDate=" + request.getStartDate().toString() + " endDate=" + request.getEndDate().toString() +
				" duration=" + String.valueOf(request.getVisitDuration()));
		LocalDate startDate = LocalDate.of(request.getStartDate().getYear(), request.getStartDate().getMonth(), request.getStartDate().getDay());
		LocalDate endDate = LocalDate.of(request.getEndDate().getYear(), request.getEndDate().getMonth(), request.getEndDate().getDay());
		ticketService.createTickets(request.getStartHour(), request.getEndHour(), startDate, endDate, request.getVisitDuration());
	}
}

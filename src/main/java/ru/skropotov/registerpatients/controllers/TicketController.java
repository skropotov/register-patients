package ru.skropotov.registerpatients.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.skropotov.registerpatients.models.Ticket;
import ru.skropotov.registerpatients.models.dto.RegisterDto;
import ru.skropotov.registerpatients.repositories.TicketRepository;
import ru.skropotov.registerpatients.services.TicketService;

@RestController()
@RequestMapping("tickets")
public class TicketController {
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketService ticketService;
	
	@GetMapping("/unused")
	public List<Ticket> getUnusedTickets(Long doctorId, String visitDate) {
		return ticketRepository.findUnusedTickets(doctorId, visitDate);
	}
	
	@PostMapping("/register")
	public void register(@RequestBody RegisterDto dto) {
		ticketService.register(dto.getTicketId(), dto.getPatientId());
	}
	
	@GetMapping("/used")
	public List<Ticket> getUsedTickets(Long patientId) {
		return ticketRepository.findByPatientId(patientId);
	}
}

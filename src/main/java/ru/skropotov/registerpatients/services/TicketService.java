package ru.skropotov.registerpatients.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.skropotov.registerpatients.models.Doctor;
import ru.skropotov.registerpatients.models.Patient;
import ru.skropotov.registerpatients.models.Ticket;
import ru.skropotov.registerpatients.repositories.DoctorRepository;
import ru.skropotov.registerpatients.repositories.PatientRepository;
import ru.skropotov.registerpatients.repositories.TicketRepository;

@Service
public class TicketService {
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired 
	PatientRepository patientRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	private Logger log = LoggerFactory.getLogger(TicketService.class);
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	public void register(Long ticketId, Long patientId) {
		Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
		if (ticket.getPatient() != null) {
			throw new RuntimeException("Ticket already used");
		}
		Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found"));
		ticket.setPatient(patient);
		ticketRepository.save(ticket);
	}
	
	private void createTicketsForDoctorPerDay(Doctor doctor, int startHour, int endHour, LocalDate currentDate, int visitDuration) {
		IntStream.iterate(startHour, i -> i + visitDuration)
		.limit(((endHour - startHour) / visitDuration))
		.forEach(y -> {
			Ticket ticket = new Ticket();
			ticket.setDoctor(doctor);
			ticket.setVisitDate(currentDate.atTime(y, 0));
			ticketRepository.save(ticket);
		});
	}
	
	private void createTicketsPerDay(int startHour, int endHour, LocalDate currentDate, int visitDuration) {
		List<Ticket> tickets = ticketRepository.findByVisitDate(Date.valueOf(currentDate));
		if (tickets.size() > 0) {
			throw new RuntimeException("Талоны за " + currentDate.format(formatter) + " уже созданы");
		}
		doctorRepository.findAll().forEach(x -> createTicketsForDoctorPerDay(x, startHour, endHour, currentDate, visitDuration)); 
	}
	
	public void createTickets(int startHour, int endHour, LocalDate startDate, LocalDate endDate, int visitDuration) {
		long daysCount = ChronoUnit.DAYS.between(startDate, endDate) + 1;
		
		List<LocalDate> dates = IntStream.iterate(0,  i -> i + 1)
				.limit(daysCount)
				.mapToObj(y -> startDate.plusDays(y))
				.filter(x -> x.getDayOfWeek().getValue() < 6)
				.collect(Collectors.toList());
		
		dates.forEach(z -> {
			try {
				createTicketsPerDay(startHour, endHour, z, visitDuration);
			}
			catch(RuntimeException e) {
				log.error(e.getMessage());
			}
		});
	}
}

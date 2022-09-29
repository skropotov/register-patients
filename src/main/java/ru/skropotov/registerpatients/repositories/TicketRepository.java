package ru.skropotov.registerpatients.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ru.skropotov.registerpatients.models.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
	@Query(value = "select * from tickets t where t.doctor_id = ? and to_char(trunc(t.visit_date), 'DD.MM.YYYY') = ? and t.patient_id is null", nativeQuery = true)
	List<Ticket> findUnusedTickets(Long doctorId, String visitDate);
	
	List<Ticket> findByPatientId(Long patientId);
	
	@Query(value = "select * from tickets t where trunc(t.visit_date) = ?", nativeQuery = true)
	List<Ticket> findByVisitDate(Date visitDate);
}

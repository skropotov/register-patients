package ru.skropotov.registerpatients.repositories;

import org.springframework.data.repository.CrudRepository;

import ru.skropotov.registerpatients.models.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {

}

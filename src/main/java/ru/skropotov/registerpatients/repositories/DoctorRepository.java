package ru.skropotov.registerpatients.repositories;

import org.springframework.data.repository.CrudRepository;

import ru.skropotov.registerpatients.models.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long>{

}

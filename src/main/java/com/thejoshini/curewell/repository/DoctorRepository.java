package com.thejoshini.curewell.repository;

import com.thejoshini.curewell.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}

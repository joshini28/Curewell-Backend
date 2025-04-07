package com.thejoshini.curewell.service;

import com.thejoshini.curewell.entity.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor>  getALlDoctor();
    Doctor AddDoctor(Doctor doctor);
    Doctor UpdateDoctor(Long id,Doctor doctor);
void deleteDoctor(Long id);
}

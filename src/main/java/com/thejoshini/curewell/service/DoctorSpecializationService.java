package com.thejoshini.curewell.service;

import com.thejoshini.curewell.payload.DoctorSpecializationDto;
import com.thejoshini.curewell.entity.DoctorSpecialization;

import java.util.List;

public interface DoctorSpecializationService {
    List<DoctorSpecialization> getAllSpecializationwithDoc();
    DoctorSpecialization addDoctorSpecialization(DoctorSpecializationDto doctorSpecialization);
    void DeleteByDoctorid(Long id,String code);


}

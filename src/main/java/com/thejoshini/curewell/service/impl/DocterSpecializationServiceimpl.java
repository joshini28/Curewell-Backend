package com.thejoshini.curewell.service.impl;

import com.thejoshini.curewell.payload.DoctorSpecializationDto;
import com.thejoshini.curewell.entity.Doctor;
import com.thejoshini.curewell.entity.DoctorSpecialization;
import com.thejoshini.curewell.entity.DoctorSpecializationId;
import com.thejoshini.curewell.entity.Specialization;
import com.thejoshini.curewell.exception.ResourceNotFoundException;
import com.thejoshini.curewell.repository.DoctorRepository;
import com.thejoshini.curewell.repository.DoctorSpecializationRepository;
import com.thejoshini.curewell.repository.SpecializationRespository;
import com.thejoshini.curewell.service.DoctorSpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class DocterSpecializationServiceimpl implements DoctorSpecializationService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    SpecializationRespository specializationRespository;
    @Autowired
    private DoctorSpecializationRepository doctorSpecializationRepository;
    @Override
    public List<DoctorSpecialization> getAllSpecializationwithDoc() {
        return doctorSpecializationRepository.findAll();
    }

    @Override
    public DoctorSpecialization addDoctorSpecialization(DoctorSpecializationDto doctorSpecialization) {


        Doctor doctor = doctorRepository.findById(doctorSpecialization.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor","DoctorId", doctorSpecialization.getDoctorId()));

        Specialization specialization = specializationRespository.findById(doctorSpecialization.getSpecializationCode())
                .orElseThrow(() -> new ResourceNotFoundException("Specialization ","SpecializationCode" , doctorSpecialization.getSpecializationCode()));

        DoctorSpecialization doctorSpecialization1 = new DoctorSpecialization();
        DoctorSpecializationId id = new DoctorSpecializationId(doctor.getId(), specialization.getCode());
        doctorSpecialization1.setId(id);
        doctorSpecialization1.setDoctor(doctor);
        doctorSpecialization1.setSpecialization(specialization);
        doctorSpecialization1.setLocalDate(LocalDate.now()); // Ensure this sets a proper date

        return doctorSpecializationRepository.save(doctorSpecialization1);
    }


    @Override
    public void DeleteByDoctorid(Long id,String code) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor","DoctorId",id));

        Specialization specialization = specializationRespository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Specialization ","SpecializationCode" , code));

        DoctorSpecializationId doctorSpecializationId=new DoctorSpecializationId(id,code);
        DoctorSpecialization doctorSpecialization=doctorSpecializationRepository.findById(doctorSpecializationId)
                .orElseThrow(()->new ResourceNotFoundException("Specialization",code,"doctor",id));

//        System.out.println(id);
        doctorSpecializationRepository.delete(doctorSpecialization);

    }


}

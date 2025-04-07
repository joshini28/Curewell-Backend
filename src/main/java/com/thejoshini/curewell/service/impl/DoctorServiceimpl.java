package com.thejoshini.curewell.service.impl;

import com.thejoshini.curewell.entity.Doctor;
import com.thejoshini.curewell.entity.DoctorSpecialization;
import com.thejoshini.curewell.entity.Surgery;
import com.thejoshini.curewell.exception.ResourceNotFoundException;
import com.thejoshini.curewell.repository.DoctorRepository;
import com.thejoshini.curewell.repository.DoctorSpecializationRepository;
import com.thejoshini.curewell.repository.SpecializationRespository;
import com.thejoshini.curewell.repository.SurgeryRepository;
import com.thejoshini.curewell.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceimpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
@Autowired
    SurgeryRepository surgeryRepository;
@Autowired
    DoctorSpecializationRepository doctorSpecializationRepository;
@Autowired
    SpecializationRespository specializationRespository;
    @Override
    public List<Doctor> getALlDoctor() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor AddDoctor(Doctor doctor) {

        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor UpdateDoctor(Long id, Doctor doctor) {
        Doctor doctor1=doctorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Doctor","DoctorId",id));
        doctor1.setName(doctor.getName()) ;
        return doctorRepository.save(doctor1);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor=doctorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Doctor","DoctorId",id));
        List<Surgery> surgery=surgeryRepository.findByDoctorId(id);
        for (int i=0;i<surgery.size();i++){
            Surgery surgery1=surgery.get(i);
            surgeryRepository.delete(surgery1);
        }
        List<DoctorSpecialization> doctorSpecializations=doctorSpecializationRepository.findByDoctorId(id);
        for (int i=0;i<doctorSpecializations.size();i++){

            DoctorSpecialization doctorSpecialization=doctorSpecializations.get(i);
//                    .orElseThrow(()->new RuntimeException("this specizlization is not of  doctor"));
            doctorSpecializationRepository.delete(doctorSpecialization);

        }
        doctorRepository.delete(doctor);
    }
}

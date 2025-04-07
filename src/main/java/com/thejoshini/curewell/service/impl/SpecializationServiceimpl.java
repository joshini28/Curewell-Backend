package com.thejoshini.curewell.service.impl;

import com.thejoshini.curewell.entity.DoctorSpecialization;
import com.thejoshini.curewell.entity.Specialization;
import com.thejoshini.curewell.entity.Surgery;
import com.thejoshini.curewell.exception.ResourceNotFoundException;
import com.thejoshini.curewell.repository.DoctorSpecializationRepository;
import com.thejoshini.curewell.repository.SpecializationRespository;
import com.thejoshini.curewell.repository.SurgeryRepository;
import com.thejoshini.curewell.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationServiceimpl implements SpecializationService {
    @Autowired
    SpecializationRespository specializationRespository;
    @Autowired
    SurgeryRepository surgeryRepository;
    @Autowired
    DoctorSpecializationRepository doctorSpecializationRepository;
    @Override
    public List<Specialization> getAllSpecialization() {
        return specializationRespository.findAll();
    }

    @Override
    public Specialization AddSpecialization(Specialization specialization) {
        String code = specialization.getCode().toUpperCase();
        Boolean exists=specializationRespository.existsById(code);

        if(exists){
            throw new ResourceNotFoundException("specialization",code);
        }

        return specializationRespository.save(specialization);
    }

    @Override
    public Specialization updateSpecialization(String code, Specialization specialization) {
        Specialization specialization1 = specializationRespository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Specialization ","SpecializationCode" ,code));
        specialization1.setName(specialization.getName());
        specialization1.setCode(specialization.getCode());
        return specializationRespository.save(specialization1);
    }

    @Override
    public void deleteSpecialization(String code) {
        Specialization specialization=specializationRespository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Specialization ","SpecializationCode" ,code));
        List<Surgery> surgery=surgeryRepository.findBySpecializationCode(code);
        for (int i=0;i<surgery.size();i++){
           Surgery surgery1=surgery.get(i);
            surgeryRepository.delete(surgery1);
        }
        List<DoctorSpecialization> doctorSpecializations=doctorSpecializationRepository.findByspecializationCode(code);
        for (int i=0;i<doctorSpecializations.size();i++){

            DoctorSpecialization doctorSpecialization=doctorSpecializations.get(i);
//                    .orElseThrow(()->new RuntimeException("this specizlization is not of  doctor"));
            doctorSpecializationRepository.delete(doctorSpecialization);

        }

        specializationRespository.delete(specialization);
    }
}

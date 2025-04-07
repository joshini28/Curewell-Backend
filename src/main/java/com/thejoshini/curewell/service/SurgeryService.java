package com.thejoshini.curewell.service;

import com.thejoshini.curewell.payload.SurgeryDto;
import com.thejoshini.curewell.entity.Surgery;

import java.util.List;

public interface SurgeryService {
    List<Surgery> getAllSurgeryoftoday();
    List<Surgery> findBysurgeryoftodayAndspecializationcode(String SpecializationCode);
    Surgery addSurgery(SurgeryDto surgeryDto);
    Surgery updateSurgery(Long id,Surgery surgery);
    void deleteSurgery(Long id);
    List<Surgery> AllSurgery();
}

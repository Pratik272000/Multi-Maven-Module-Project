package com.app.service;

import com.app.dao.PatientRepository;
import com.app.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(int id){
        return patientRepository.findById(id);
    }


}

package com.app.service;

import com.app.dao.PatientRepository;
import com.app.dtos.PatientDetailsDto;
import com.app.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    public List<PatientDetailsDto> getPatients(){

        List<Patient> list= patientRepository.findAll();
        List<PatientDetailsDto> result=new ArrayList<>();
        for(Patient p:list){
            result.add(patientToPatientDetailsDtoConvertor(p));
        }
        return result;
    }

    private PatientDetailsDto patientToPatientDetailsDtoConvertor(Patient p) {
        return new PatientDetailsDto(p.getName(),p.getGender(),
                p.getBloodGroup(),p.getAge(),p.getContact(),p.getAddress().getCity()
                ,p.getAddress().getLane(),p.getAddress().getPincode());
    }


    public Optional<PatientDetailsDto> getPatientById(int id){

        Optional<Patient> patient = patientRepository.findById(id);
        if(!patient.isEmpty()){
            return Optional.of(patientToPatientDetailsDtoConvertor(patient.get()));
        }else return Optional.empty();
    }


}

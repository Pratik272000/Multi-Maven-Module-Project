package com.app.service;

import com.app.dao.PatientRepository;
import com.app.dtos.PatientDetailsDto;
import com.app.dtos.PatientDto;
import com.app.entities.Address;
import com.app.entities.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class PatientServiceTest {
@Mock
    PatientRepository patientRepository;
@InjectMocks
PatientService patientService;
    @Test
    void getPatients() {
        List<Patient> list=new ArrayList<>(Arrays.asList(new Patient(),new Patient()));
        Address a1=new Address(1,"Satara","Satara","900990");
        Address a2=new Address(2,"Pune","Pune","832923");
        list.get(0).setAddress(a1);
        list.get(1).setAddress(a2);
        when(patientRepository.findAll()).thenReturn(list);
        List<PatientDetailsDto> result=patientService.getPatients();
        assertNotNull(result);
        assertEquals(2,result.size());
    }

    @Test
    void getPatientById() {
        int id=1;
        Patient p=new Patient(id,"Shubham","Male","O+ve",24,"9988776677");
        Address a1=new Address(1,"Satara","Satara","900990");
        p.setAddress(a1);
        when(patientRepository.findById(id)).thenReturn(Optional.of(p));
        Optional<PatientDetailsDto> result = patientService.getPatientById(id);
        assertNotNull(result);
        assertEquals(result.get().getName(),p.getName());
        assertEquals(result.get().getCity(),p.getAddress().getCity());
        assertEquals(result.get().getGender(),p.getGender());
        assertEquals(result.get().getContact(),p.getContact());
        assertEquals(result.get().getBloodGroup(),p.getBloodGroup());
        assertEquals(result.get().getPincode(),p.getAddress().getPincode());
    }

    @Test
    void addPatient() {
        Patient p=new Patient(1,"Shubham","Male","O+ve",24,"9988776677");
        Address a1=new Address(1,"Satara","Satara","900990");
        p.setAddress(a1);
        when(patientRepository.save(any(Patient.class))).thenAnswer(invocation -> invocation.getArgument(0));
        PatientDto patienDto=new PatientDto("Shubham","Male","O+ve",24,"9988776677");
        PatientDto result = patientService.addPatient(patienDto);
        assertNotNull(result);
        verify(patientRepository).save(any(Patient.class));


    }
}
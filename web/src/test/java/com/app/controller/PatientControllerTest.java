package com.app.controller;

import com.app.dtos.PatientDetailsDto;
import com.app.dtos.PatientDto;
import com.app.entities.Address;
import com.app.entities.Patient;
import com.app.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Mock
    PatientService patientService;
    @InjectMocks
    PatientController patientController;
    private MockMvc mockMvc;
    ObjectMapper mapper=new ObjectMapper();
    com.fasterxml.jackson.databind.ObjectWriter objectWriter=mapper.writer();
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }
    @Test
    void getAllPatient() throws Exception {
        PatientDetailsDto pd1=new PatientDetailsDto("Omkar","Male","O+ve",24,"8892991234","satara","satara","327889");
        PatientDetailsDto pd2=new PatientDetailsDto("Shubham","Male","O+ve",24,"239849823","Pune","satara","892309");
        List<PatientDetailsDto> list=new ArrayList<>(Arrays.asList(pd1,pd2));
        when(patientService.getPatients()).thenReturn(list);
        mockMvc.perform(get("/patient/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Omkar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].age").value(24))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].contact").value("8892991234"));

    }

    @Test
    void getPatientById() throws Exception {
        PatientDetailsDto pd1=new PatientDetailsDto("Omkar","Male","O+ve",24,"8892991234","satara","satara","327889");
        int id=1;
        when(patientService.getPatientById(id)).thenReturn(Optional.of(pd1));
        mockMvc.perform(get("/patient/get/{id}",id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Omkar"));


    }

    @Test
    void addPatient() throws Exception {
             PatientDto p1=new PatientDto("Shubham","Male","O+ve",24,"9988776677");
        when(patientService.addPatient(any(PatientDto.class))).thenReturn(p1);
        mockMvc.perform(post("/patient/add").content(mapper.writeValueAsString(p1)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Shubham"));

    }
}
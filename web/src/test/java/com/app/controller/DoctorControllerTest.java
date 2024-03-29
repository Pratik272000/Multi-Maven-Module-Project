package com.app.controller;

import com.app.entities.Doctor;
import com.app.service.DoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorControllerTest {
    @Mock
    DoctorService doctorService;
    @InjectMocks
    DoctorController doctorController;

    private MockMvc mockMvc;
    ObjectMapper mapper=new ObjectMapper();
    com.fasterxml.jackson.databind.ObjectWriter objectWriter=mapper.writer();
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
    }

    @Test
    public void getDoctorTest() throws Exception {
        List<Doctor> list = new ArrayList<>(Arrays.asList(new Doctor(101, "shubham", "cardiac"),
                new Doctor(102, "prabhat", "eye")));
        when(doctorService.getDoctr()).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/doctors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(list.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(list.get(0).getId()));

    }
    @Test
    public void getDoctorByIdTest() throws Exception{
        Doctor doc=new Doctor(1,"Shubham","Cardiac");
        when(doctorService.getDoctorById(1)).thenReturn(Optional.of(doc));
        mockMvc.perform(MockMvcRequestBuilders.get("/doctor/1")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(doc.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(doc.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.specialist").value(doc.getSpecialist()));
    }
    @Test
    public void getDoctorById_failure_Test() throws Exception{
        when(doctorService.getDoctorById(999)).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/doctor/{id}",999)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }


}
package com.app.controller;

import com.app.dtos.AppointmentDto;
import com.app.dtos.TakeAppointmentDto;
import com.app.entities.Appointment;
import com.app.entities.Doctor;
import com.app.entities.Patient;
import com.app.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class AppointmentControllerTest {

    @Mock
    AppointmentService appointmentService;
    @InjectMocks
    AppointmentController appointmentController;
    private MockMvc mockMvc;
    ObjectMapper mapper=new ObjectMapper();
    ObjectMapper objectMapper = new ObjectMapper();

    com.fasterxml.jackson.databind.ObjectWriter objectWriter=mapper.writer();
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }
    @Test
    void getAllAppointment() throws Exception{
        AppointmentDto a1=new AppointmentDto("Shubham","Omkar",LocalDateTime.of(2024,3,8,1,20));
        AppointmentDto a2=new AppointmentDto("Satish","Rahul",LocalDateTime.of(2024,3,8,1,20));
       List<AppointmentDto> list=new ArrayList<>(Arrays.asList(a1,a2));
        when(appointmentService.getAllAppointmentList()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/apt/getall"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].patientName").value("Shubham"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].doctorName").value("Rahul"));


    }

    @Test
    void doAppointment() throws Exception{

    }

    @Test
    void deleteAppointment()throws Exception {
        AppointmentDto a1=new AppointmentDto("Shubham","Omkar",LocalDateTime.of(2024,3,8,1,20));
        when(appointmentService.deleteAppointmentById(1)).thenReturn(a1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/apt/delete/{id}",1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.patientName").value("Shubham"));
    }
}

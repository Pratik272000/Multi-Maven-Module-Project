package com.app.service;

import com.app.dao.AppointmentRepository;
import com.app.dao.DoctorRepositiry;
import com.app.dao.PatientRepository;
import com.app.dtos.AppointmentDto;
import com.app.dtos.TakeAppointmentDto;
import com.app.entities.Appointment;
import com.app.entities.Doctor;
import com.app.entities.Patient;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    AppointmentRepository appointmentRepository;
    @Mock
    PatientRepository patientRepository;
    @Mock
    DoctorRepositiry doctorRepositiry;
    @InjectMocks
    AppointmentService appointmentService;

    @Test
    void doAppointmentTest() {

        Appointment a1=new Appointment();
        Patient p1=new Patient("Omkar","Male","O+ve",24,"90909090");
        Doctor d1=new Doctor(1,"Satish","eye");
        a1.setPatient(p1);
        a1.setDoctor(d1);
        a1.setAppointmentDateTime(LocalDateTime.of(2024,3,8,1,2));a1.setPatient(p1);
        TakeAppointmentDto takeAppointmentDto=new TakeAppointmentDto(a1.getPatient().getId(),a1.getDoctor().getId(),a1.getAppointmentDateTime());
        when(appointmentRepository.save(any(Appointment.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(patientRepository.findById(a1.getPatient().getId())).thenReturn(Optional.of(p1));
        when(doctorRepositiry.findById(a1.getDoctor().getId())).thenReturn(Optional.of(d1));
        AppointmentDto result=appointmentService.doAppointment(takeAppointmentDto);
        assertNotNull(result);
        assertEquals(result.getDate(),a1.getAppointmentDateTime());
    }

    @Test
    void getAllAppointmentListTest() {
        Appointment a1=new Appointment();
        Patient p1=new Patient("Omkar","Male","O+ve",24,"90909090");
        Doctor d1=new Doctor(1,"Satish","eye");
        a1.setPatient(p1);
        a1.setDoctor(d1);
        a1.setAppointmentDateTime(LocalDateTime.of(2024,3,8,1,2));a1.setPatient(p1);
        Appointment a2=new Appointment();
        Patient p2=new Patient("Omkar","Male","O+ve",24,"90909090");
        Doctor d2=new Doctor(2,"Rahul","cardiac");
        a2.setPatient(p2);
        a2.setDoctor(d2);
        a2.setAppointmentDateTime(LocalDateTime.of(2024,3,8,1,2));
        List<Appointment> list=new ArrayList<>(Arrays.asList(a1,a2));
        when(appointmentRepository.findAll()).thenReturn(list);
        List<AppointmentDto> result=appointmentService.getAllAppointmentList();
        assertNotNull(result);
        assertEquals(2,result.size());
    }

    @Test
    void deleteAppointmentById() {
        Appointment a1=new Appointment();
        Patient p1=new Patient("Omkar","Male","O+ve",24,"90909090");
        Doctor d1=new Doctor(1,"Satish","eye");
        a1.setId(1L);
        a1.setPatient(p1);
        a1.setDoctor(d1);
        a1.setAppointmentDateTime(LocalDateTime.of(2024,3,8,1,2));a1.setPatient(p1);
        when(appointmentRepository.findById(Math.toIntExact(a1.getId()))).thenReturn(Optional.of(a1));
        AppointmentDto result = appointmentService.deleteAppointmentById(Math.toIntExact(a1.getId()));
        assertEquals(result.getPatientName(),"Omkar");
        verify(appointmentRepository).delete(any(Appointment.class));


    }
}
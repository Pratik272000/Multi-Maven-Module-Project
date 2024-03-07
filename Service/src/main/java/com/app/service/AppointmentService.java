package com.app.service;

import com.app.dao.AppointmentRepository;
import com.app.dao.DoctorRepositiry;
import com.app.dao.PatientRepository;
import com.app.dtos.AppointmentDto;
import com.app.dtos.TakeAppointmentDto;
import com.app.entities.Appointment;
import com.app.entities.Doctor;
import com.app.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DoctorRepositiry doctorRepositiry;
    @Autowired
    PatientRepository patientRepository;

    public AppointmentDto doAppointment(TakeAppointmentDto takeAppointmentDto){
        Appointment appointment=new Appointment();
        Optional<Doctor> doctor=doctorRepositiry.findById(takeAppointmentDto.getDoctorId());
        Optional<Patient> patient=patientRepository.findById(takeAppointmentDto.getPatientId());
        appointment.setDoctor(doctor.get());
        appointment.setPatient(patient.get());
        appointment.setAppointmentDateTime(takeAppointmentDto.getLocalDateTime());
        Appointment a=appointmentRepository.save(appointment);
        return new AppointmentDto(a.getPatient().getName(),a.getDoctor().getName(),a.getAppointmentDateTime());
    }
    public List<AppointmentDto> getAllAppointmentList(){
        List<AppointmentDto> result=new ArrayList<>();
        List<Appointment> list=appointmentRepository.findAll();
        for(Appointment a:list){
            result.add(new AppointmentDto(a.getPatient().getName(),a.getDoctor().getName(),a.getAppointmentDateTime()));
        }
        return result;
    }
    public AppointmentDto deleteAppointmentById(int id){
        Optional<Appointment> appointment=appointmentRepository.findById(id);
        appointmentRepository.delete(appointment.get());
        return new AppointmentDto(appointment.get().getPatient().getName(),appointment.get().getDoctor().getName(),appointment.get().getAppointmentDateTime());
    }
}

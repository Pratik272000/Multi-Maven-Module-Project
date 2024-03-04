package com.app.service;

import com.app.dao.DoctorRepositiry;
import com.app.entities.Doctor;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepositiry doctorRepositiry;
    @PostConstruct
    public void initDoctor(){
        doctorRepositiry.saveAll(Stream.of(new Doctor(101,"Shubham","Cardiac"),
                new Doctor(201,"Prabhat","eye")).collect(Collectors.toList()));
    }

    public List<Doctor> getDoctr(){
        return doctorRepositiry.findAll();
    }




}

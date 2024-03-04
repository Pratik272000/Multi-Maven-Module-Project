package com.app.controller;

import com.app.entities.Doctor;
import com.app.service.DoctorService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @GetMapping("/doctors")
    public List<Doctor> getDoctor(){
        return doctorService.getDoctr();
    }

}

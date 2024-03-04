package com.app.controller;

import com.app.entities.Doctor;
import com.app.service.DoctorService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@RestController
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @GetMapping("/doctors")
    public List<Doctor> getDoctor(){
        return doctorService.getDoctr();
    }
    @GetMapping("/doctor/{id}")
    public ResponseEntity getDoctorById(@PathVariable int id){
        Optional<Doctor> optionalDoctor = doctorService.getDoctorById(id);
        if (optionalDoctor.isEmpty()) {
            return ResponseEntity.notFound().build(); // This should return a 404 status
        }
        return ResponseEntity.ok(optionalDoctor.get());
    }

}

package com.app.controller;

import com.app.entities.Doctor;
import com.app.service.DoctorService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody Doctor doctor){
        return ResponseEntity.ok(doctorService.addDocto(doctor));
    }

}

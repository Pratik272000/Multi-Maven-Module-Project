package com.app.controller;

import com.app.dtos.PatientDto;
import com.app.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@CrossOrigin("*")
public class PatientController {
@Autowired
    PatientService patientService;
@GetMapping("/all")
    public ResponseEntity getAllPatient(){
    return ResponseEntity.ok(patientService.getPatients());
}
@GetMapping("/get/{id}")
    public ResponseEntity getPatientById(@PathVariable int id){
    return ResponseEntity.ok(patientService.getPatientById(id));
}

@PostMapping("/add")
    public ResponseEntity addPatient(@RequestBody PatientDto patientDto){
    return ResponseEntity.ok(patientService.addPatient(patientDto));
}

}

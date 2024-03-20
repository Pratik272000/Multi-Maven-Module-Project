package com.app.controller;

import com.app.dtos.AppointmentDto;
import com.app.dtos.TakeAppointmentDto;
import com.app.service.AppointmentService;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apt")
@CrossOrigin("*")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @GetMapping("/getall")
    public List<AppointmentDto> getAllAppointment(){
        return appointmentService.getAllAppointmentList();
    }
    @PostMapping("/add")
    public AppointmentDto doAppointment(@RequestBody TakeAppointmentDto takeAppointmentDto){
        System.out.println("Add Appointmetn Method");
        System.out.println(takeAppointmentDto);
        return appointmentService.doAppointment(takeAppointmentDto);
    }
    @DeleteMapping("/delete/{id}")
    public AppointmentDto deleteAppointment(@PathVariable int id){
        return appointmentService.deleteAppointmentById(id);
    }

}

package com.app.controller;

import com.app.dtos.AppointmentDto;
import com.app.dtos.TakeAppointmentDto;
import com.app.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apt")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @GetMapping("/getall")
    public List<AppointmentDto> getAllAppointment(){
        return appointmentService.getAllAppointmentList();
    }
    @PostMapping("/add")
    public AppointmentDto doAppointment(@RequestBody TakeAppointmentDto takeAppointmentDto){
        return appointmentService.doAppointment(takeAppointmentDto);
    }
    @DeleteMapping("/delete/{id}")
    public AppointmentDto deleteAppointment(@PathVariable int id){
        return appointmentService.deleteAppointmentById(id);
    }

}

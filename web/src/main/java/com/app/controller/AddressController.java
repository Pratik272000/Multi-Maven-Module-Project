package com.app.controller;

import com.app.dao.AddressRepository;
import com.app.entities.Address;
import com.app.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @PostMapping("/add/{id}")
    public ResponseEntity addPatientAddress(@PathVariable int id, @RequestBody Address address){

        return ResponseEntity.ok(addressService.addPatientAddress(id,address));
    }
}

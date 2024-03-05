package com.app.service;


import com.app.dao.AddressRepository;
import com.app.dao.PatientRepository;
import com.app.entities.Address;
import com.app.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AddressRepository addressRepository;
    public Optional<Address> addPatientAddress(int id, Address address){
        Optional<Patient> p=patientRepository.findById(id);
        if(p.isEmpty())
            return Optional.empty();
        addressRepository.save(address);
        p.get().setAddress(address);
        patientRepository.save(p.get());
        return Optional.ofNullable(address);
    }

}

package com.app.service;

import com.app.dao.AddressRepository;
import com.app.dao.PatientRepository;
import com.app.entities.Address;
import com.app.entities.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    PatientRepository patientRepository;
    @Mock
    AddressRepository addressRepository;
    @InjectMocks
    AddressService addressService;

    @Test
    void addPatientAddress() {
        int id=1;
        Patient p=new Patient();
        Address address=new Address(1,"Nashik","Nashik","123321");
        when(patientRepository.findById(1)).thenReturn(Optional.of(p));
        when(patientRepository.save(any(Patient.class))).thenAnswer(invocation->invocation.getArgument(0));
        when(addressRepository.save(any(Address.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Optional<Address> result = addressService.addPatientAddress(1, address);
        assertNotNull(result);
        assertEquals(result.get().getCity(),address.getCity());
        verify(patientRepository).findById(1);
        verify(patientRepository).save(any(Patient.class));
        verify(addressRepository).save(any(Address.class));

    }
    @Test
    void addPatientAddressFaulire_Test(){
        int id=999;
        Address address=new Address(1,"Nashik","Nashik","123321");
        when(patientRepository.findById(id)).thenReturn(Optional.empty());
        Optional<Address> result = addressService.addPatientAddress(id, address);
        assertTrue(result.isEmpty());

    }
}
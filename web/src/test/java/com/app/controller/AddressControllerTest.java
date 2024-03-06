package com.app.controller;

import com.app.entities.Address;
import com.app.entities.Patient;
import com.app.service.AddressService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AddressControllerTest {
    @Mock
    AddressService addressService;
    @InjectMocks
    AddressController addressController;
    private MockMvc mockMvc;
    ObjectMapper mapper=new ObjectMapper();
    com.fasterxml.jackson.databind.ObjectWriter objectWriter=mapper.writer();
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
    }


    @Test
    void addPatientAddressTest()throws Exception {
        int id=1;
        Address a=new Address(1,"Satara","Satara","234234");
        when(addressService.addPatientAddress(id,a)).thenReturn(Optional.of(a));
        mockMvc.perform(MockMvcRequestBuilders.post("/address/add/{id}",id).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(a)))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }
}
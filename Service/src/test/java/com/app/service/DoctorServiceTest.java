package com.app.service;

import com.app.dao.DoctorRepositiry;
import com.app.entities.Doctor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {
    @Mock
    DoctorRepositiry doctorRepositiry;
    @InjectMocks
    DoctorService doctorService;
    @Captor
    private ArgumentCaptor<List<Doctor>> doctorListCaptor;

    @Test
    public void initDoctorTest(){
        List<Doctor> list=new ArrayList<>(Arrays.asList(new Doctor(101,"Shubham","Cardiac"),
                new Doctor(201,"Prabhat","eye")));
        doctorService.initDoctor();
        verify(doctorRepositiry).saveAll(doctorListCaptor.capture());
    }


    @Test
    public void getDoctorTest(){
        List<Doctor> list=new ArrayList<>(Arrays.asList(new Doctor(101,"Shubam","eye"),
                                                        new Doctor(102,"Prabhat","Cardiac")));
        when(doctorRepositiry.findAll()).thenReturn(list);
        List<Doctor> result=doctorService.getDoctr();
        assertNotNull(result);
        assertEquals(result.size(),2);
        assertEquals(result.get(0).getName(),(list.get(0).getName()));
        when(doctorRepositiry.findAll()).thenReturn(new ArrayList<>());
        List<Doctor> emptyResult = doctorService.getDoctr();
        assertNotNull(emptyResult);
        assertTrue(emptyResult.isEmpty());
    }


}
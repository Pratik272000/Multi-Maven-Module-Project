package com.app.service;

import com.app.dao.HomeRepository;
import com.app.dtos.LoginDto;
import com.app.entities.AccessData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeService {
    @Autowired
    HomeRepository homeRepository;
    public AccessData register(AccessData accessData){
        return homeRepository.save(accessData);
    }
    public Optional<Object> login(LoginDto loginDto){
        AccessData a=homeRepository.findByEmail(loginDto.getEmail());
        System.out.println("in login service");
        if(a!=null)
        if(a.getPassword().equals(loginDto.getPassword()))
            return Optional.of(a);
         return Optional.empty();
    }

}

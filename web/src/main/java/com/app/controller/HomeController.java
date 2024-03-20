package com.app.controller;

import com.app.dtos.LoginDto;
import com.app.entities.AccessData;
import com.app.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    HomeService homeService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        System.out.println("in controller");
        return
        ResponseEntity.status(HttpStatusCode.valueOf(200)).body(homeService.login(loginDto));
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody AccessData accessData){
        return ResponseEntity.ok(homeService.register(accessData));
    }
}

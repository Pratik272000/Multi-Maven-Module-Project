package com.app.controller;

import com.app.dtos.LoginDto;
import com.app.entities.AccessData;
import com.app.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    HomeService homeService;
    @GetMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(homeService.login(loginDto));
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody AccessData accessData){
        return ResponseEntity.ok(homeService.register(accessData));
    }
}

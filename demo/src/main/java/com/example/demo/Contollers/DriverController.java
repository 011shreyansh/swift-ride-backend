package com.example.demo.Contollers;

import com.example.demo.DTOs.DriverDTO;
import com.example.demo.DTOs.DriverResponseDTO;
import com.example.demo.Entities.Driver;
import com.example.demo.Services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/driver")

public class DriverController {
    @Autowired
    private DriverService dService;

    @PostMapping("/create")
    public ResponseEntity<DriverResponseDTO> create(@RequestBody DriverDTO request){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        Optional<Driver> driver=dService.create(username,request);
        if(driver.isPresent()){
            DriverResponseDTO d=new DriverResponseDTO();
            d.setId(driver.get().getId());
            return new ResponseEntity<>(d,HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

}

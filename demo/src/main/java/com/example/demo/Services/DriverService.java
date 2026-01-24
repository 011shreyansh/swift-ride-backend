package com.example.demo.Services;

import com.example.demo.DTOs.DriverDTO;
import com.example.demo.Entities.Driver;
import com.example.demo.Entities.User;
import com.example.demo.Repos.DriverRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Data
@Service
public class DriverService {

    @Autowired
    private DriverRepo drepo;
    @Autowired
    private UserService userService;

    @Transactional
    public Optional<Driver> create(String username, DriverDTO request){
        Optional<User> userOpt=userService.getUser(username);
        if(userOpt.isPresent()){
            User user=userOpt.get();
            if(user.getRoles().contains("DRIVER")){
                return Optional.empty();
            }
            Driver driver= new Driver();
            driver.setUser(user);
            user.getRoles().add("DRIVER");
            userService.save(user);
             driver.setCarNo(request.getCarNo());
             driver.setCarModel(request.getCarModel());
            return Optional.of(drepo.save(driver));
        }
        return Optional.empty();

    }
}

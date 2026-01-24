package com.example.demo.Repos;

import com.example.demo.Entities.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriverRepo extends MongoRepository<Driver,String> {
}

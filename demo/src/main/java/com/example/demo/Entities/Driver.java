package com.example.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @DBRef
    private User user;
    @Id
    private String id;
    private String carNo;
    private String carModel;
    private List<Ride> ridelist=new ArrayList<>();
}

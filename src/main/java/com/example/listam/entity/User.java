package com.example.listam.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Table(name = "user")
@Data

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    private String email;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserType userType;
    @DateTimeFormat(pattern = "yyyy-MM-hh")
    private Date dOB;
}

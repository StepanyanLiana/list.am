package com.example.listam.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hashtag")
@Data

public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}

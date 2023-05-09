package com.example.listam.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "category")
@Data

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
}

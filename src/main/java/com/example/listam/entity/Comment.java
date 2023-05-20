package com.example.listam.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "comment")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "comment")
    private String commentText;
    @ManyToOne
    private Item item;
    @ManyToOne
    private User user;
    private Date commentDate;
}

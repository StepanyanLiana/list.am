package com.example.listam.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "item")
@Data

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
    private String imgName;

    @ManyToMany
    @JoinTable(name = "item_hashtag",
    joinColumns = @JoinColumn(name = "item_id"),
    inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    List<Hashtag> hashtagList;

}

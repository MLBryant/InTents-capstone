package com.capstone.intents.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String password;

    @OneToMany(mappedBy = "campground")
    private List<Campground> campgrounds = new ArrayList<>();
}

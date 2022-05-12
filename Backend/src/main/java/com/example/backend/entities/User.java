package com.example.backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usr")
@Getter
@Setter
public class User {

    @Id
    private String id;

    private String name;

    private String email;

    private String picture;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserProgress> userProgressSet;
}

package com.example.backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "project")
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer pointsToLevelUp = 100;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "project_admin",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> admins;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "project_user",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> users;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "project_achievement",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "achievement_id")}
    )
    private Set<Achievement> achievements;

    @OneToMany
    private Set<Board> boards;
}

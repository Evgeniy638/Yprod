package com.example.backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_progress")
@Getter
@Setter
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long level;

    private Long points;

    @ManyToOne
    private User user;

    @ManyToOne
    private Project project;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_progress_achievement",
            joinColumns = {@JoinColumn(name = "user_progress_id")},
            inverseJoinColumns = {@JoinColumn(name = "achievement_id")}
    )
    private Set<Achievement> achievements;

}

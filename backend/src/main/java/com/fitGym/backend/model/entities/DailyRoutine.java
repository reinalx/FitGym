package com.fitGym.backend.model.entities;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class DailyRoutine {
    private Long id;
    private String name;
    private String description;
    private Routine routine;
    private Set<Workout> workouts = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "routineId")
    public Routine getRoutine() {
        return routine;
    }
    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    @OneToMany(mappedBy = "dailyRoutine")
    public Set<Workout> getWorkouts() {
        return workouts;
    }
    public void setWorkouts(Set<Workout> workouts) {
        this.workouts = workouts;
    }
}
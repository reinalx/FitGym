package com.fitGym.backend.model.entities;


import com.fitGym.backend.model.utils.Day;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

//TODO: Añadir campo para que dia es cada una de las dailies
@Entity
public class DailyRoutine {
    private Long id;
    private String name;
    private String description;
    private Routine routine;
    private Set<Workout> workouts = new HashSet<>();
    private Day day;

    public DailyRoutine() {}

    public DailyRoutine(String name, String description, Day day, Routine routine) {
        this.name = name;
        this.description = description;
        this.routine = routine;
        this.day = day;
    }

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

    @Column(name = "day")
    public Day getDay() {
        return day;
    }
    public void setDay(Day day) {
        this.day = day;
    }

    @OneToMany(mappedBy = "dailyRoutine")
    public Set<Workout> getWorkouts() {
        return workouts;
    }
    public void setWorkouts(Set<Workout> workouts) {
        this.workouts = workouts;
    }

    public void addWorkout(Workout workout) {
        workouts.add(workout);
        workout.setDailyRoutine(this);
    }
    public void removeWorkout(Workout workout) {
        workouts.remove(workout);
        workout.setDailyRoutine(null);
    }
}
package com.fitGym.backend.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Routine {
    private Long id;
    private String name;
    private String description;
    private String type;
    private Date startDate;
    private Date endDate;
    private Set<DailyRoutine> dailyRoutines = new HashSet<>();
    private Set<RoutineUser> routineUsers = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @OneToMany(mappedBy = "routine")
    public Set<DailyRoutine> getDailyRoutines() {
        return dailyRoutines;
    }

    public void setDailyRoutines(Set<DailyRoutine> dailyRoutines) {
        this.dailyRoutines = dailyRoutines;
    }

    @OneToMany(mappedBy = "routine")
    public Set<RoutineUser> getRoutineUsers() {
        return routineUsers;
    }

    public void setRoutineUsers(Set<RoutineUser> routineUsers) {
        this.routineUsers = routineUsers;
    }

}


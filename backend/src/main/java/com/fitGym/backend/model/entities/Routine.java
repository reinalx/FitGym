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
    private boolean visibility;
    private Set<DailyRoutine> dailyRoutines = new HashSet<>();
    private User user;

    public Routine() {}

    public Routine(String name, String description, String type, Date startDate, Date endDate, boolean visibility, User user) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.visibility = visibility;
        this.user = user;
    }
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

    public boolean isVisibility() {
        return visibility;
    }
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
    @OneToMany(mappedBy = "routine")
    public Set<DailyRoutine> getDailyRoutines() {
        return dailyRoutines;
    }

    public void setDailyRoutines(Set<DailyRoutine> dailyRoutines) {
        this.dailyRoutines = dailyRoutines;
    }

    public void addDailyRoutine(DailyRoutine dailyRoutine) {
        dailyRoutines.add(dailyRoutine);
        dailyRoutine.setRoutine(this);
    }
    public void removeDailyRoutine(DailyRoutine dailyRoutine) {
        dailyRoutines.remove(dailyRoutine);
        dailyRoutine.setRoutine(null);
    }

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }



}


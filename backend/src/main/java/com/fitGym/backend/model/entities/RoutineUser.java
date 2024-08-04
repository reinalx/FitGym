package com.fitGym.backend.model.entities;

import jakarta.persistence.*;

@Entity
public class RoutineUser {
    private long id;
    private Routine routine;
    private User user;

    public RoutineUser() {
    }

    public RoutineUser(long id, Routine routine, User user) {
        this.id = id;
        this.routine = routine;
        this.user = user;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "routineId")
    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routineId) {
        this.routine = routineId;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
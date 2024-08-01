package com.fitGym.backend.model.entities;

import jakarta.persistence.*;

@Entity
public class RoutineUser {
    private long id;
    private Routine routineId;
    private User userId;

    public RoutineUser() {
    }

    public RoutineUser(long id, Routine routineId, User userId) {
        this.id = id;
        this.routineId = routineId;
        this.userId = userId;
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
    public Routine getRoutineId() {
        return routineId;
    }

    public void setRoutineId(Routine routineId) {
        this.routineId = routineId;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

}
package main.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoutineUser {
    private long id;
    private long routineId;
    private long userId;

    public RoutineUser() {}

    public RoutineUser(long id, long routineId, long userId) {
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

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "routineId")
    public long getRoutineId() {
        return routineId;
    }
    public void setRoutineId(long routineId) {
        this.routineId = routineId;
    }

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
package com.fitGym.backend.model.entities;


import jakarta.persistence.*;

@Entity
public class UserFriend {
    private long id;
    private User user1;
    private User user2;

    public UserFriend() {}

    public UserFriend(long id, User user1, User user2) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
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
    @JoinColumn(name = "user1Id")
    public User getUser1() {
        return user1;
    }
    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user2Id")
    public User getUser2() {
        return user2;
    }
    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
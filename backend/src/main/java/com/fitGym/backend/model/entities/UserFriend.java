package com.fitGym.backend.model.entities;


import jakarta.persistence.*;

@Entity
public class UserFriend {
    private long id;
    private User user1Id;
    private User user2Id;

    public UserFriend() {}

    public UserFriend(long id, User user1Id, User user2Id) {
        this.id = id;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
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
    public User getUser1Id() {
        return user1Id;
    }
    public void setUser1Id(User user1Id) {
        this.user1Id = user1Id;
    }

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user2Id")
    public User getUser2Id() {
        return user2Id;
    }
    public void setUser2Id(User user2Id) {
        this.user2Id = user2Id;
    }
}
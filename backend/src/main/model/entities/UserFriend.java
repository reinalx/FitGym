package main.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserFriend {
    private long id;
    private long user1Id;
    private long user2Id;

    public UserFriend() {}

    public UserFriend(long id, long user1Id, long user2Id) {
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
    public long getUser1Id() {
        return user1Id;
    }
    public void setUser1Id(long user1Id) {
        this.user1Id = user1Id;
    }

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user2Id")
    public long getUser2Id() {
        return user2Id;
    }
    public void setUser2Id(long user2Id) {
        this.user2Id = user2Id;
    }
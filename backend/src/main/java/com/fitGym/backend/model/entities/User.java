package com.fitGym.backend.model.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class User {
    public  enum RoleType {USER};

    private long id;
    private String name;
    private String email;
    private String password;
    private RoleType role;
    private String firstName;
    private String lastName;
    private Set<UserFriend> userFriends = new HashSet<>();

    public User() {}

    public User(long id, String name, String email, String password, RoleType role, String firstName, String lastName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public RoleType getRole() {
        return role;
    }
    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToMany(mappedBy = "user1")
    public Set<UserFriend> getUserFriends() {
        return userFriends;
    }
    public void setUserFriends(Set<UserFriend> userFriends) {
        this.userFriends = userFriends;
    }

}
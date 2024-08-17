package com.fitGym.backend.model.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class User {
    public  enum RoleType {USER};

    private Long id;
    private String userName;
    private String email;
    private String password;
    private RoleType role;
    private String firstName;
    private String lastName;
    private Set<User> userFriends = new HashSet<>();
    private Set<Exercise> exercises = new HashSet<>();
    private Set<Routine> routines = new HashSet<>();

    public User() {}

    public User(long id, String userName, String email, String password, RoleType role, String firstName, String lastName) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    //TODO: Poner correctamente la anotación
    @OneToMany(mappedBy = "user1")
    public Set<User> getUserFriends() {
        return userFriends;
    }
    public void setUserFriends(Set<User> userFriends) {
        this.userFriends = userFriends;
    }

    @OneToMany(mappedBy = "user")
    public Set<Exercise> getExercises() {
        return exercises;
    }
    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    @OneToMany(mappedBy = "user")
    public Set<Routine> getRoutines() {
        return routines;
    }
    public void setRoutines(Set<Routine> routines) {
        this.routines = routines;
    }

}
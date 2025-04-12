package com.fitGym.backend.rest.dtos;


public class ExerciseDto {

    private Long id;
    private String name;
    private String description;
    private String muscleTarget;
    private String muscleGroup;
    private String picture;
    private Long userId;

    public ExerciseDto() {}

    public ExerciseDto(Long id, String name, String description, String muscleTarget, String muscleGroup, String picture, Long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.muscleTarget = muscleTarget;
        this.muscleGroup = muscleGroup;
        this.picture = picture;
        this.userId = userId;
    }

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

    public String getMuscleTarget() {
        return muscleTarget;
    }

    public void setMuscleTarget(String muscleTarget) {
        this.muscleTarget = muscleTarget;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

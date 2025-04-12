package com.fitGym.backend.rest.dtos;

public class SetsDto {
    private Long id;
    private int numberSet;
    private int reps;
    private float kg;
    private Long workoutId;

    public SetsDto() {
    }

    public SetsDto(Long id, int numberSet, int reps, float kg, Long workoutId) {
        this.id = id;
        this.numberSet = numberSet;
        this.reps = reps;
        this.kg = kg;
        this.workoutId = workoutId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberSet() {
        return numberSet;
    }

    public void setNumberSet(int numberSet) {
        this.numberSet = numberSet;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public float getKg() {
        return kg;
    }

    public void setKg(float kg) {
        this.kg = kg;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }
}

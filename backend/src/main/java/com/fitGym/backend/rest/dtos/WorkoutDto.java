package com.fitGym.backend.rest.dtos;

import java.util.List;

public class WorkoutDto {

    private Long id;
    private int targetSets;
    private int targetReps;
    private float targetKg;
    private Long exerciseId;
    private Long dailyRoutineId;
    private List<SetsDto> sets;

    public WorkoutDto() {
    }

    public WorkoutDto(Long id, int targetSets, int targetReps, float targetKg, Long exerciseId, Long dailyRoutineId, List<SetsDto> sets) {
        this.id = id;
        this.targetSets = targetSets;
        this.targetReps = targetReps;
        this.targetKg = targetKg;
        this.exerciseId = exerciseId;
        this.dailyRoutineId = dailyRoutineId;
        this.sets = sets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTargetSets() {
        return targetSets;
    }

    public void setTargetSets(int targetSets) {
        this.targetSets = targetSets;
    }

    public int getTargetReps() {
        return targetReps;
    }

    public void setTargetReps(int targetReps) {
        this.targetReps = targetReps;
    }

    public float getTargetKg() {
        return targetKg;
    }

    public void setTargetKg(float targetKg) {
        this.targetKg = targetKg;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Long getDailyRoutineId() {
        return dailyRoutineId;
    }

    public void setDailyRoutineId(Long dailyRoutineId) {
        this.dailyRoutineId = dailyRoutineId;
    }

    public List<SetsDto> getSets() {
        return sets;
    }

    public void setSets(List<SetsDto> sets) {
        this.sets = sets;
    }


}

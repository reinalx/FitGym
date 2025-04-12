package com.fitGym.backend.rest.dtos;

import com.fitGym.backend.model.utils.Day;

import java.util.List;

public class DailyRoutineDto {

    private Long id;
    private String name;
    private String description;
    private Long routineId;
    private int day;
    private List<WorkoutDto> workouts;

    public DailyRoutineDto() {
    }

    public DailyRoutineDto(Long id, String name, String description, Long routineId, List<WorkoutDto> workouts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.routineId = routineId;
        this.workouts = workouts;
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

    public Long getRoutineId() {
        return routineId;
    }

    public void setRoutineId(Long routineId) {
        this.routineId = routineId;
    }

    public List<WorkoutDto> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<WorkoutDto> workouts) {
        this.workouts = workouts;
    }

    public Day getDay() {
        return Day.fromNumber(day);
    }

    public void setDay(int day) {
        this.day = day;
    }


}

package com.fitGym.backend.rest.dtos;

import java.util.List;

public class RoutineDto {

    private Long id;
    private String name;
    private String description;
    private String type;
    private List<DailyRoutineDto> dailyRoutines;
    private boolean visibility;
    private Long userId;

    public RoutineDto() {
    }

    public RoutineDto(Long id, String name, String description, String type, List<DailyRoutineDto> dailyRoutines, boolean visibility, Long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.dailyRoutines = dailyRoutines;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DailyRoutineDto> getDailyRoutines() {
        return dailyRoutines;
    }

    public void setDailyRoutines(List<DailyRoutineDto> dailyRoutines) {
        this.dailyRoutines = dailyRoutines;
    }

    public boolean isVisibility() {
        return visibility;
    }
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}

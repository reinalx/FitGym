package com.fitGym.backend.rest.dtos;

import com.fitGym.backend.model.entities.DailyRoutine;

import java.util.List;
import java.util.stream.Collectors;

import static com.fitGym.backend.rest.dtos.WorkoutConversor.toWorkoutDto;

public class DailyRoutineConversor {

    private DailyRoutineConversor() {}

    public static DailyRoutineDto toDailyRoutineDto(DailyRoutine dailyRoutine) {
        List<WorkoutDto> workouts = dailyRoutine.getWorkouts().stream().map(i -> toWorkoutDto(i)).collect(Collectors.toList());

        return new DailyRoutineDto(dailyRoutine.getId(), dailyRoutine.getName(), dailyRoutine.getDescription(), dailyRoutine.getRoutine().getId(), workouts);
    }

    public static List<DailyRoutineDto> toDailyRoutineDtos(List<DailyRoutine> dailyRoutines) {
        return dailyRoutines.stream().map(i -> toDailyRoutineDto(i)).collect(Collectors.toList());
    }
}

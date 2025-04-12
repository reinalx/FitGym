package com.fitGym.backend.rest.dtos;

import com.fitGym.backend.model.entities.Workout;

import java.util.List;
import java.util.stream.Collectors;

import static com.fitGym.backend.rest.dtos.SetsConversor.toSetsDto;

public class WorkoutConversor {

    private WorkoutConversor() {
    }

    public static WorkoutDto toWorkoutDto(Workout workout) {
        List<SetsDto> sets = workout.getSets().stream().map(i -> toSetsDto(i)).collect(Collectors.toList());
        return new WorkoutDto(workout.getId(), workout.getTargetSets(), workout.getTargetReps(), workout.getTargetKg(), workout.getExercise().getId(), workout.getDailyRoutine().getId(), sets);
    }

    public static List<WorkoutDto> toWorkoutDtos(List<Workout> workouts) {
        return workouts.stream().map(i -> toWorkoutDto(i)).collect(Collectors.toList());
    }
}

package com.fitGym.backend.rest.dtos;

import com.fitGym.backend.model.entities.Exercise;

import java.util.List;
import java.util.stream.Collectors;

public class ExerciseConversor {

    private ExerciseConversor() {}

    public static ExerciseDto toExerciseDto(Exercise exercise) {
        return new ExerciseDto(exercise.getId(), exercise.getName(), exercise.getDescription(), exercise.getMuscleTarget(), exercise.getMuscleGroup(), exercise.getPicture(), exercise.getUser().getId());
    }

    public static List<ExerciseDto> toExerciseDtos(List<Exercise> exercises) {
        return exercises.stream().map( e -> toExerciseDto(e)).collect(Collectors.toList());
    }

}

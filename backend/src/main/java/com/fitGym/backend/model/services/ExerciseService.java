package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.Exercise;
import com.fitGym.backend.model.utils.Block;

public interface ExerciseService {

     Exercise findExerciseById(long id);

     Block<Exercise> findExercises(long exeerciseId, String muscleTarget, String muscleGroup, int page, int size);

     void addExercise(Exercise exercise);

     void updateExercise(Exercise exercise);

     void deleteExercise(Exercise exercise);
}

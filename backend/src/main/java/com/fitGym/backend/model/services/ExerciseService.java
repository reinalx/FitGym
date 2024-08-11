package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.Exercise;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.utils.Block;

import java.util.List;

public interface ExerciseService {

     Exercise findExerciseById(long id) throws InstanceNotFoundException;

     Block<Exercise> findExercises(long exerciseId, String name, String muscleTarget, String muscleGroup, int page, int size);

     //CAMBIAR TODO ESTO
     void addExercise(String name, String description, String muscleTarget, String muscleGroup);

     Exercise updateExercise(Long exerciseId, String name, String description, String muscleTarget, String muscleGroup) throws InstanceNotFoundException;

     void deleteExercise(Long exerciseId) throws InstanceNotFoundException;
}

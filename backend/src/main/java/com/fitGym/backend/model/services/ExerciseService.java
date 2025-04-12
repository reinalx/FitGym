package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.Exercise;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;
import com.fitGym.backend.model.utils.Block;

import java.util.List;

public interface ExerciseService {

     //TODO: ADAPTAR BUSQUEDA DE EJERCICIOS
     Exercise findExerciseById(Long userId, Long exerciseId) throws InstanceNotFoundException, PermissionException;

     Block<Exercise> findExercises(Long userId, String name, String muscleTarget, String muscleGroup, int page, int size);


     Exercise addExercise(Long userId,String name, String description, String muscleTarget, String muscleGroup, String picture) throws InstanceNotFoundException;

     Exercise updateExercise(Long userId, Long exerciseId, String name, String description, String muscleTarget, String muscleGroup, String picture) throws InstanceNotFoundException, PermissionException;

     Exercise deleteExercise(Long userId, Long exerciseId) throws InstanceNotFoundException, PermissionException;
}

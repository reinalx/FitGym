package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.*;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;

public interface PermissionChecker {


    //Algunas no las usare porque no hacen falta
    User checkUser(Long userId) throws InstanceNotFoundException;


    Routine checkRoutineExistAndBelongToUser(Long routineId, Long userId) throws InstanceNotFoundException, PermissionException;

    Routine checkRoutineExistAndBelongToUserOrIsVisible(Long routineId, Long userId) throws InstanceNotFoundException, PermissionException;

    DailyRoutine checkDailyRoutineExistAndBelongToUser(Long userId ,Long dailyRoutineId) throws InstanceNotFoundException, PermissionException;

    Workout checkWorkoutExistAndBelongToUser(Long userId ,Long workoutId) throws InstanceNotFoundException, PermissionException;

    Exercise checkExerciseExistAndBelongToUser(Long exerciseId, Long userId) throws InstanceNotFoundException, PermissionException;

    Exercise checkExerciseExistAndBelongToUserOrIsVisible(Long exerciseId, Long userId) throws InstanceNotFoundException, PermissionException;

    Sets checkSetsExistAndBelongToUser(Long userId, Long setsId) throws InstanceNotFoundException, PermissionException;
}

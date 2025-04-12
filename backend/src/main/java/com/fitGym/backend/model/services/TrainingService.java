package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.Sets;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;
import com.fitGym.backend.model.exceptions.WorkoutWithoutSetsException;

import java.util.List;

public interface TrainingService {

    Sets findSetsById(Long setsId) throws InstanceNotFoundException;

    List<Sets> findAllSetsFromWorkout(Long workoutId) throws InstanceNotFoundException, WorkoutWithoutSetsException;

    Sets findSetsByNumber(Long workoutId, Long userId, int numberSet) throws InstanceNotFoundException, PermissionException;

    Sets addSets(Long userId, Long workoutId, int reps, float kg) throws InstanceNotFoundException, PermissionException;
    Sets removeSets(Long userId, Long setsId) throws InstanceNotFoundException, PermissionException;
    Sets updateSets(Long userId, Long setsId, int  reps, float kg) throws InstanceNotFoundException, PermissionException;
}

package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.Sets;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;

public interface TrainingService {

    //PENSAR MUCHO MAS, NO CUADRA ALGO
    Sets addSets(Long userId, Long workoutId, int numberSet, int reps, float kg) throws InstanceNotFoundException, PermissionException;
    Sets removeSets(Long userId, Long setsId) throws InstanceNotFoundException, PermissionException;
    Sets updateSets(Long userId, Long setsId, int  reps, float kg) throws InstanceNotFoundException, PermissionException;
}

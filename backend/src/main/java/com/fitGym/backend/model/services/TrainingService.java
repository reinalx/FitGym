package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.Sets;

public interface TrainingService {

    //PENSAR MUCHO MAS, NO CUADRA ALGO
    void addSets(Long userId, Long workoutId, Sets sets);
    void removeSets(Long userId, Long setsId);
    void updateSets(Long userId, Long setsId, Sets sets);
}

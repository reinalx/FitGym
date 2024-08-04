package com.fitGym.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseDao extends CrudRepository<Exercise, Long>, CustomizedExerciseDao{
    Slice<Exercise> findByMuscleTargetOrderByName(String muscleTarget, Pageable pageable);

    Slice<Exercise> findByMuscleGroupOrderByName(String muscleGroup, Pageable pageable);

    Slice<Exercise> findByMuscleTargetAndGroupOrderByName(String muscleTarget, String group, Pageable pageable);
}
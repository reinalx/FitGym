package com.fitGym.backend.model.entities;

import org.springframework.data.domain.Slice;

public class CustomizedExerciseDaoImpl implements CustomizedExerciseDao {

    @Override
    public Slice<Exercise> find(Long exerciseId, String muscleTarget, String muscleGroup , int page, int size) {
        return null;
    }
}

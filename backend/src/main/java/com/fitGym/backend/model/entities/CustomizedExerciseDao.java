package com.fitGym.backend.model.entities;

import org.springframework.data.domain.Slice;

public interface CustomizedExerciseDao {

    Slice<Exercise> find(Long userId, String name, String muscleTarget, String muscleGroup ,int page, int size);
}

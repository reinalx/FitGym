package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.Exercise;
import com.fitGym.backend.model.entities.ExerciseDao;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.utils.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExercisesServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseDao exerciseDao;

    @Override
    public Exercise findExerciseById(long id) throws InstanceNotFoundException {

        Optional<Exercise> exercise = exerciseDao.findById(id);

        if(!exercise.isPresent()){
            throw new InstanceNotFoundException("project.entities.exercise", id);
        }

        return exercise.get();
    }

    @Override
    public Block<Exercise> findExercises(long exerciseId, String name, String muscleTarget, String muscleGroup, int page, int size) {
        Slice<Exercise> exercise = exerciseDao.find(exerciseId, name, muscleTarget, muscleGroup, page, size);

        return new Block<>(exercise.getContent(), exercise.hasNext());
    }

    @Override
    public void addExercise(Exercise exercise) {

    }

    @Override
    public void updateExercise(Exercise exercise) {

    }

    @Override
    public void deleteExercise(Exercise exercise) {

    }


}

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
    public void addExercise(String name, String description, String muscleTarget, String muscleGroup) {

        Exercise exercise = new Exercise();

        exercise.setName(name);
        exercise.setDescription(description);
        exercise.setMuscleTarget(muscleTarget);
        exercise.setMuscleGroup(muscleGroup);

        exerciseDao.save(exercise);
    }

    @Override
    public Exercise updateExercise(Long exerciseId, String name, String description, String muscleTarget, String muscleGroup) throws InstanceNotFoundException {

        Optional<Exercise> exercise = exerciseDao.findById(exerciseId);
        if(!exercise.isPresent()){
            throw new InstanceNotFoundException("project.entities.exercise", exerciseId);
        }

        exercise.get().setName(name);
        exercise.get().setDescription(description);
        exercise.get().setMuscleTarget(muscleTarget);
        exercise.get().setMuscleGroup(muscleGroup);

        exerciseDao.save(exercise.get());

        return exercise.get();
    }

    @Override
    public void deleteExercise(Long exerciseId) throws InstanceNotFoundException {

        Optional<Exercise> exercise = exerciseDao.findById(exerciseId);
        if(!exercise.isPresent()){
            throw new InstanceNotFoundException("project.entities.exercise", exerciseId);
        }

        exerciseDao.delete(exercise.get());
    }


}

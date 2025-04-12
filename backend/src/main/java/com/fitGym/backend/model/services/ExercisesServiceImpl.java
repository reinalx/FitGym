package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.Exercise;
import com.fitGym.backend.model.entities.ExerciseDao;
import com.fitGym.backend.model.entities.UserDao;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;
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
    private UserDao userDao;
    @Autowired
    private ExerciseDao exerciseDao;
    @Autowired
    private PermissionChecker permissionChecker;

    @Override
    public Exercise findExerciseById(Long userId, Long exerciseId) throws InstanceNotFoundException, PermissionException {

        return permissionChecker.checkExerciseExistAndBelongToUserOrIsVisible(exerciseId, userId);

    }

    //TODO: Añadir restricciones de permisos
    @Override
    public Block<Exercise> findExercises(Long userId, String name, String muscleTarget, String muscleGroup, int page, int size) {
        Slice<Exercise> exercise = exerciseDao.find(userId, name, muscleTarget, muscleGroup, page, size);

        return new Block<>(exercise.getContent(), exercise.hasNext());
    }


    //Esta función es para los usuarios, Futuro añadir una para admins
    @Override
    public Exercise addExercise(Long userId, String name, String description, String muscleTarget, String muscleGroup, String picture) throws InstanceNotFoundException {

        Exercise exercise = new Exercise();

        exercise.setName(name);
        exercise.setDescription(description);
        exercise.setMuscleTarget(muscleTarget);
        exercise.setMuscleGroup(muscleGroup);
        exercise.setUser(permissionChecker.checkUser(userId));
        exercise.setPicture(picture);

        exerciseDao.save(exercise);

        return exercise;
    }

    @Override
    public Exercise updateExercise(Long userId, Long exerciseId, String name, String description, String muscleTarget, String muscleGroup, String picture) throws InstanceNotFoundException, PermissionException {

        Exercise exercise = permissionChecker.checkExerciseExistAndBelongToUser(exerciseId, userId);

        exercise.setName(name);
        exercise.setDescription(description);
        exercise.setMuscleTarget(muscleTarget);
        exercise.setMuscleGroup(muscleGroup);
        exercise.setPicture(picture);

        exerciseDao.save(exercise);
        return exercise;
    }

    @Override
    public Exercise deleteExercise(Long userId, Long exerciseId) throws InstanceNotFoundException, PermissionException {
        Exercise exercise = permissionChecker.checkExerciseExistAndBelongToUserOrIsVisible(exerciseId, userId);

        exerciseDao.delete(exercise);

        return exercise;
    }


}

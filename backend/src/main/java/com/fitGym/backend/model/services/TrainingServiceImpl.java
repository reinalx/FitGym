package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.*;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;
import com.fitGym.backend.model.exceptions.WorkoutWithoutSetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private PermissionChecker permissionChecker;

    @Autowired
    private SetsDao setsDao;

    @Autowired
    private WorkoutDao workoutDao;

    @Override
    public Sets findSetsById(Long userId) throws InstanceNotFoundException {
        Optional<Sets> sets = setsDao.findById(userId);
        if (sets.isEmpty()) {
            throw new InstanceNotFoundException("project.entities.sets", userId);
        }
        return sets.get();
    }

    @Override
    public List<Sets> findAllSetsFromWorkout(Long workoutId) throws InstanceNotFoundException, WorkoutWithoutSetsException {

        Optional<Workout> workout = workoutDao.findById(workoutId);
        if (workout.isEmpty()) {
            throw new InstanceNotFoundException("project.entities.workout", workoutId);
        }
        if(workout.get().getSets().isEmpty()){
            throw new WorkoutWithoutSetsException();
        }
        return workout.get().getSets().stream().toList();
    }

    @Override
    public Sets findSetsByNumber(Long workoutId, Long userId, int numberSet) throws InstanceNotFoundException, PermissionException {
        Workout workout = permissionChecker.checkWorkoutExistAndBelongToUser(userId, workoutId);

        Optional<Sets> sets = workout.getSetsByNumber(numberSet);
        if (sets.isEmpty()) {
            throw new InstanceNotFoundException("project.entities.sets", workoutId);
        }
        return sets.get();
    }

    @Override
    public Sets addSets(Long userId, Long workoutId,  int reps, float kg) throws InstanceNotFoundException, PermissionException {

        Workout workout = permissionChecker.checkWorkoutExistAndBelongToUser(userId, workoutId);

        Sets sets = new Sets();
        sets.setNumberSet(workout.getNumberOfSets()+1);
        sets.setReps(reps);
        sets.setKg(kg);
        sets.setWorkout(permissionChecker.checkWorkoutExistAndBelongToUser(userId, workoutId));

        workout.addSet(sets);
        setsDao.save(sets);

        return sets;

    }

    @Override
    public Sets removeSets(Long userId, Long setsId) throws InstanceNotFoundException, PermissionException {

        Sets sets = permissionChecker.checkSetsExistAndBelongToUser(userId, setsId);
        setsDao.delete(sets);
        return sets;

    }

    @Override
    public Sets updateSets(Long userId, Long setsId, int reps, float kg) throws InstanceNotFoundException, PermissionException {

        Sets set = permissionChecker.checkSetsExistAndBelongToUser(userId, setsId);

        set.setReps(reps);
        set.setKg(kg);
        setsDao.save(set);

        return set;
    }
}


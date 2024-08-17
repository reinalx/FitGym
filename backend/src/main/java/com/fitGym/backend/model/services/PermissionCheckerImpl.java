package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.*;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class PermissionCheckerImpl implements PermissionChecker {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ExerciseDao exerciseDao;

    @Autowired
    private RoutineDao routineDao;

    @Autowired
    private DailyRoutineDao dailyRoutineDao;

    @Autowired
    private WorkoutDao workoutDao;

    @Autowired
    private SetsDao setsDao;


    @Override
    public User checkUser(Long userId) throws InstanceNotFoundException {
        Optional<User> user = userDao.findById(userId);

        if (!user.isPresent()) {
            throw new InstanceNotFoundException("project.entities.user", userId);
        }

        return user.get();
    }

    @Override
    public Exercise checkExerciseExistAndBelongToUser(Long exerciseId, Long userId) throws InstanceNotFoundException, PermissionException {

        Optional<Exercise> exercise = exerciseDao.findById(exerciseId);

        if (!exercise.isPresent()) {
            throw new InstanceNotFoundException("project.entities.exercise", exerciseId);
        }

        if (!exercise.get().getUser().getId().equals(userId)) {
            throw new PermissionException();
        }

        return exercise.get();
    }

    @Override
    public Routine checkRoutineExistAndBelongToUser(Long routineId, Long userId) throws InstanceNotFoundException, PermissionException {

        Optional<Routine> routine = routineDao.findById(routineId);

        if (!routine.isPresent()) {
            throw new InstanceNotFoundException("project.entities.routine", routineId);
        }

        if (!routine.get().getUser().getId().equals(userId)) {
            throw new PermissionException();
        }

        return routine.get();
    }

    @Override
    public Routine checkRoutineExistAndBelongToUserOrIsVisible(Long routineId, Long userId) throws InstanceNotFoundException, PermissionException {

        Optional<Routine> routine = routineDao.findById(routineId);

        if (!routine.isPresent()) {
            throw new InstanceNotFoundException("project.entities.routine", routineId);
        }

        if (!routine.get().getUser().getId().equals(userId) && !routine.get().isVisibility()) {
            throw new PermissionException();
        }

        return routine.get();
    }

    @Override
    public DailyRoutine checkDailyRoutineExistAndBelongToUser(Long userId ,Long dailyRoutineId) throws InstanceNotFoundException, PermissionException {

        Optional<DailyRoutine> dailyRoutine = dailyRoutineDao.findById(dailyRoutineId);

        if (!dailyRoutine.isPresent()) {
            throw new InstanceNotFoundException("project.entities.dailyRoutine", dailyRoutineId);
        }

        if (!dailyRoutine.get().getRoutine().getUser().getId().equals(userId)) {
            throw new PermissionException();
        }

        return dailyRoutine.get();

    }

    @Override
    public Workout checkWorkoutExistAndBelongToUser(Long userId ,Long workoutId) throws InstanceNotFoundException, PermissionException {

        Optional<Workout> workout = workoutDao.findById(workoutId);

        if (!workout.isPresent()) {
            throw new InstanceNotFoundException("project.entities.workout", workoutId);
        }
        if(!workout.get().getDailyRoutine().getRoutine().getUser().getId().equals(userId)){
            throw new PermissionException();
        }

        return workout.get();
    }

    @Override
    public Exercise checkExerciseExistAndBelongToUserOrIsVisible(Long exerciseId, Long userId) throws InstanceNotFoundException, PermissionException {

        Optional<Exercise> exercise = exerciseDao.findById(exerciseId);

        if (!exercise.isPresent()) {
            throw new InstanceNotFoundException("project.entities.exercise", exerciseId);
        }

        if(exercise.get().getUser() != null){
            if(!exercise.get().getUser().getId().equals(userId)){
                throw new PermissionException();
            }
        }

        return exercise.get();
    }

    @Override
    public Sets checkSetsExistAndBelongToUser(Long userId, Long setsId) throws InstanceNotFoundException, PermissionException {
        Optional<Sets> sets = setsDao.findById(setsId);

        if (!sets.isPresent()) {
            throw new InstanceNotFoundException("project.entities.sets", setsId);
        }

        if(!sets.get().getWorkout().getDailyRoutine().getRoutine().getUser().getId().equals(userId)){
            throw new PermissionException();
        }
        return sets.get();
    }


}

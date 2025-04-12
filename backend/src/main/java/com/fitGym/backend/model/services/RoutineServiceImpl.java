package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.*;

import com.fitGym.backend.model.exceptions.*;
import com.fitGym.backend.model.utils.Block;
import com.fitGym.backend.model.utils.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoutineServiceImpl implements RoutineService {

    @Autowired
    private RoutineDao routineDao;

    @Autowired
    private DailyRoutineDao dailyRoutineDao;

    @Autowired
    private WorkoutDao workoutDao;

    @Autowired
    private PermissionChecker permissionChecker;

    private Boolean isFreeDay(Long routineId, Day day) throws InstanceNotFoundException {
        Optional<Routine> routine = routineDao.findById(routineId);
        if (routine.isEmpty()) {
            throw new InstanceNotFoundException("project.entities.routine", routineId);
        }
        return routine.get().getDailyRoutines().stream().anyMatch(dailyRoutine -> dailyRoutine.getDay().equals(day));
    }

    @Override
    @Transactional(readOnly = true)

    //TODO: Desarrollar metodo con permisos
    public List<Routine> findAllRoutines() {return routineDao.findAll(Sort.by(Sort.Direction.ASC, "name"));}

    @Override
    public List<Routine> findAllRoutinesUser(Long userId) {

        return routineDao.findByUserIdOrderByName(userId);
    }

    @Override
    public List<Routine> findRoutinesByNameAndUserId(String name, Long userId) {
        return routineDao.findByNameLikeAndUserIdOrderByName(name, userId);
    }

    //TODO: Desarrollar metodo
    @Override
    public Block<Routine> findRoutineByName(String name, Long userId, int page, int size) {

        return new Block<>(null, false );
    }


    //Añadir excepcion para cuando se intenta acceder a una rutina que no esta visible y no pertenece al usuario
    @Override
    public Routine findRoutineById(Long routineId, Long userId ) throws InstanceNotFoundException, PermissionException {

        return permissionChecker.checkRoutineExistAndBelongToUserOrIsVisible(routineId, userId);
    }



    //TODO: Quitar parametros que no sean necesarios
    @Override
    public Routine addNewRoutine(Long userId, String name, String description, String type, Date startDate, Date endDate, boolean visibility) throws InstanceNotFoundException {
        Routine routine = new Routine();
        routine.setName(name);
        routine.setDescription(description);
        routine.setType(type);
        routine.setStartDate(startDate);
        routine.setEndDate(endDate);
        routine.setVisibility(visibility);

        User user = permissionChecker.checkUser(userId);
        routine.setUser(user);
        routineDao.save(routine);

        return routine;
    }

    @Override
    public Routine updateRoutine(Long userId, Long routineId, String name, String description, String type, Date startDate, Date endDate, boolean visibility) throws InstanceNotFoundException, PermissionException {

        Routine routine = permissionChecker.checkRoutineExistAndBelongToUser(routineId, userId);

        routine.setName(name);
        routine.setDescription(description);
        routine.setType(type);
        routine.setStartDate(startDate);
        routine.setEndDate(endDate);
        routine.setVisibility(visibility);

        routineDao.save(routine);

        return routine;

    }

    @Override
    public Routine deleteRoutine(Long userId, Long routineId) throws InstanceNotFoundException, PermissionException {

        Routine routine = permissionChecker.checkRoutineExistAndBelongToUser(routineId, userId);

        routineDao.delete(routine);

        return routine;
    }

    @Override
    public DailyRoutine findDailyRoutineById(Long dailyRoutineId) throws InstanceNotFoundException {

        Optional<DailyRoutine> dailyRoutine = dailyRoutineDao.findById(dailyRoutineId);

        if (dailyRoutine.isEmpty()) {
            throw new InstanceNotFoundException("project.entities.dailyRoutine", dailyRoutineId);
        }
        return dailyRoutine.get();
    }

    @Override
    public List<DailyRoutine> findAllDailyRoutinesFromRoutine(Long routineId) throws InstanceNotFoundException, RoutineWithoutDailyRoutineException {

        Optional<Routine> routine = routineDao.findById(routineId);

        if (routine.isEmpty()) {
            throw new InstanceNotFoundException("project.entities.routine", routineId);
        }
        if(routine.get().getDailyRoutines().isEmpty()){
            throw new RoutineWithoutDailyRoutineException();
        }
        return routine.get().getDailyRoutines().stream().toList();

    }

    @Override
    public DailyRoutine addDailyRoutineToRoutine(Long userId, Long routineId, String name, String description, Day day) throws PermissionException, InstanceNotFoundException {

        Routine routine = permissionChecker.checkRoutineExistAndBelongToUser(routineId, userId);

        DailyRoutine dailyRoutine = new DailyRoutine();
        dailyRoutine.setName(name);
        dailyRoutine.setDescription(description);
        dailyRoutine.setRoutine(routine);
        if(isFreeDay(routineId, day)){
            dailyRoutine.setDay(day);
        }else{
            throw new DiferentDailyRoutinesSameDayException();
        }


        routine.addDailyRoutine(dailyRoutine);
        dailyRoutineDao.save(dailyRoutine);

        return dailyRoutine;


    }

    @Override
    public DailyRoutine deleteDailyRoutineFromRoutine(Long userId, Long dailyRoutineId) throws PermissionException, InstanceNotFoundException {

        DailyRoutine dailyRoutine = permissionChecker.checkDailyRoutineExistAndBelongToUser(userId, dailyRoutineId);

        Routine routine = dailyRoutine.getRoutine();
        routine.removeDailyRoutine(dailyRoutine);

        dailyRoutineDao.delete(dailyRoutine);

        return dailyRoutine;
    }

    @Override
    public DailyRoutine updateDailyRoutine(Long userId, Long dailyRoutineId, String name, String description, Day day) throws DiferentDailyRoutinesSameDayException, PermissionException, InstanceNotFoundException {

        DailyRoutine dailyRoutine = permissionChecker.checkDailyRoutineExistAndBelongToUser(userId, dailyRoutineId);

        dailyRoutine.setName(name);
        dailyRoutine.setDescription(description);
        if(isFreeDay(dailyRoutine.getRoutine().getId(), day)){
            dailyRoutine.setDay(day);
        }else{
            throw new DiferentDailyRoutinesSameDayException();
        }

        dailyRoutineDao.save(dailyRoutine);

        return dailyRoutine;
    }



    @Override
    public Workout findWorkoutById(Long workoutId) throws InstanceNotFoundException {

        Optional<Workout> workout = workoutDao.findById(workoutId);

        if (workout.isEmpty()) {
            throw new InstanceNotFoundException("project.entities.workout", workoutId);
        }

        return workout.get();
    }

    @Override
    public List<Workout> findAllWorkoutsFromDailyRoutine(Long dailyRoutineId) throws InstanceNotFoundException, DailyRoutineWithoutWorkoutException {

        Optional<DailyRoutine> dailyRoutine = dailyRoutineDao.findById(dailyRoutineId);

        if (dailyRoutine.isEmpty()) {
            throw new InstanceNotFoundException("project.entities.dailyRoutine", dailyRoutineId);
        }
        if(dailyRoutine.get().getWorkouts().isEmpty()){
            throw new DailyRoutineWithoutWorkoutException();
        }
        return dailyRoutine.get().getWorkouts().stream().toList();
    }

    @Override
    public Workout addWorkoutToDailyRoutine(Long userId, int targetSets, int targetReps, float targetKg, Long exerciseId, Long dailyRoutineId) throws InstanceNotFoundException, PermissionException {

        DailyRoutine dailyRoutine = permissionChecker.checkDailyRoutineExistAndBelongToUser(userId, dailyRoutineId);

        Workout workout = new Workout();
        workout.setTargetSets(targetSets);
        workout.setTargetReps(targetReps);
        workout.setTargetKg(targetKg);
        workout.setExercise(permissionChecker.checkExerciseExistAndBelongToUserOrIsVisible(exerciseId, userId));
        workout.setDailyRoutine(dailyRoutine);

        dailyRoutine.addWorkout(workout);
        workoutDao.save(workout);

        return workout;
    }


    @Override
    public Workout deleteWorkoutFromDailyRoutine(Long userId, Long workoutId) throws PermissionException, InstanceNotFoundException {

        Workout workout = permissionChecker.checkWorkoutExistAndBelongToUser(userId, workoutId);

        DailyRoutine dailyRoutine = workout.getDailyRoutine();
        dailyRoutine.removeWorkout(workout);

        workoutDao.delete(workout);

        return workout;

    }

    @Override
    public Workout updateWorkout(Long userId, Long workoutId, int targetSets, int targetReps, float targetKg) throws PermissionException, InstanceNotFoundException {


        Workout workout = permissionChecker.checkWorkoutExistAndBelongToUser(userId, workoutId);

        workout.setTargetSets(targetSets);
        workout.setTargetReps(targetReps);
        workout.setTargetKg(targetKg);

        workoutDao.save(workout);

        return workout;
    }


    //TODO: DESARROLLAR EN UN FUTURO

    @Override
    public void addExistingRoutine(Long userId, Long routineId) {

    }

    @Override
    public void deleteFullRoutine(Long userId, Long routineId) {

    }
}

package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.*;

import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;
import com.fitGym.backend.model.utils.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoutineServiceImpl implements RoutineService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoutineDao routineDao;

    @Autowired
    private DailyRoutineDao dailyRoutineDao;

    @Autowired
    private WorkoutDao workoutDao;

    @Autowired
    private PermissionChecker permissionChecker;


    @Override
    public List<Routine> findAllRoutinesUser(Long userId) {

        List<Routine> routines = routineDao.findByUserIdOrderByName(userId);

        return routines;
    }


    //Añadir excepcion para cuando se intenta acceder a una rutina que no esta visible y no pertenece al usuario
    @Override
    public Routine findRoutineById(Long routineId, Long userId ) throws InstanceNotFoundException, PermissionException {

        return permissionChecker.checkRoutineExistAndBelongToUserOrIsVisible(routineId, userId);
    }

    @Override
    public Routine addNewRoutine(Long userId, String name, String description, String type, Date startDate, Date endDate, Boolean visibility) throws InstanceNotFoundException {
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
    public Routine updateRoutine(Long userId, Long routineId, String name, String description, String type, Date startDate, Date endDate, Boolean visibility) throws InstanceNotFoundException, PermissionException {

        Routine routine = permissionChecker.checkRoutineExistAndBelongToUserOrIsVisible(routineId, userId);

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

        Routine routine = permissionChecker.checkRoutineExistAndBelongToUserOrIsVisible(routineId, userId);

        routineDao.delete(routine);

        return routine;
    }

    @Override
    public DailyRoutine findDailyRoutineById(Long dailyRoutineId) throws InstanceNotFoundException {

        Optional<DailyRoutine> dailyRoutine = dailyRoutineDao.findById(dailyRoutineId);

        if (!dailyRoutine.isPresent()) {
            throw new InstanceNotFoundException("project.entities.dailyRoutine", dailyRoutineId);
        }
        return dailyRoutine.get();
    }

    @Override
    public DailyRoutine addDailyRoutineToRoutine(Long userId, Long routineId, String name, String description) throws PermissionException, InstanceNotFoundException {

        DailyRoutine dailyRoutine = new DailyRoutine();
        dailyRoutine.setName(name);
        dailyRoutine.setDescription(description);
        dailyRoutine.setRoutine(permissionChecker.checkRoutineExistAndBelongToUser(routineId, userId));

        dailyRoutineDao.save(dailyRoutine);

        return dailyRoutine;


    }

    @Override
    public DailyRoutine deleteDailyRoutineFromRoutine(Long userId, Long dailyRoutineId) throws PermissionException, InstanceNotFoundException {

        DailyRoutine dailyRoutine = permissionChecker.checkDailyRoutineExistAndBelongToUser(userId, dailyRoutineId);

        dailyRoutineDao.delete(dailyRoutine);

        return dailyRoutine;
    }

    @Override
    public DailyRoutine updateDailyRoutine(Long userId, Long dailyRoutineId, String name, String description) throws PermissionException, InstanceNotFoundException {

        DailyRoutine dailyRoutine = permissionChecker.checkDailyRoutineExistAndBelongToUser(userId, dailyRoutineId);

        dailyRoutine.setName(name);
        dailyRoutine.setDescription(description);

        dailyRoutineDao.save(dailyRoutine);

        return dailyRoutine;
    }



    @Override
    public Workout findWorkoutById(Long workoutId) throws InstanceNotFoundException {

        Optional<Workout> workout = workoutDao.findById(workoutId);

        if (!workout.isPresent()) {
            throw new InstanceNotFoundException("project.entities.workout", workoutId);
        }

        return null;
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

        workoutDao.save(workout);

        return workout;
    }


    @Override
    public Workout deleteWorkoutFromDailyRoutine(Long userId, Long workoutId) throws PermissionException, InstanceNotFoundException {

        Workout workout = permissionChecker.checkWorkoutExistAndBelongToUser(userId, workoutId);

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

    //AQUI SE BUSCAN TODAS LAS RUTINAS QUE SEAN VISIBLES Y LAS QUE SON DEL USUARIO, HABRÍA QUE MODIFICAR EL DAO
    @Override
    public Block<Routine> findAllRoutines(int page, int size) {
        return null;
    }

    @Override
    public void addExistingRoutine(Long userId, Long routineId) {

    }
}

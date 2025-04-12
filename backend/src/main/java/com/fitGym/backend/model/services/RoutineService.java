package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.DailyRoutine;
import com.fitGym.backend.model.entities.Exercise;
import com.fitGym.backend.model.entities.Routine;
import com.fitGym.backend.model.entities.Workout;
import com.fitGym.backend.model.exceptions.*;
import com.fitGym.backend.model.utils.Block;
import com.fitGym.backend.model.utils.Day;

import java.util.Date;
import java.util.List;

public interface RoutineService  {

    //TODO
    List<Routine> findAllRoutines();

    List<Routine> findAllRoutinesUser(Long userId);

    List<Routine> findRoutinesByNameAndUserId(String name, Long userId );

    //TODO
    Block<Routine> findRoutineByName(String name, Long userId, int page, int size);

    Routine findRoutineById(Long routineId, Long userId ) throws InstanceNotFoundException, PermissionException;

    Routine addNewRoutine(Long userId, String name, String description, String type, Date startDate, Date endDate, boolean visibility) throws InstanceNotFoundException;

    Routine updateRoutine(Long userId, Long routineId, String name, String description, String type, Date startDate, Date endDate, boolean visibility) throws InstanceNotFoundException, PermissionException;

    Routine deleteRoutine(Long userId, Long routineId) throws InstanceNotFoundException, PermissionException;


    //TODO: Meter el atributo de dias en todas las funciones del servicio IMPORTANTE
    DailyRoutine findDailyRoutineById( Long dailyRoutineId) throws InstanceNotFoundException, PermissionException;

    List<DailyRoutine> findAllDailyRoutinesFromRoutine(Long routineId) throws InstanceNotFoundException, RoutineWithoutDailyRoutineException;

    DailyRoutine addDailyRoutineToRoutine(Long userId, Long routineId, String name, String description, Day day) throws DiferentDailyRoutinesSameDayException, PermissionException, InstanceNotFoundException;

    DailyRoutine deleteDailyRoutineFromRoutine(Long userId, Long dailyRoutineId) throws PermissionException, InstanceNotFoundException;

    DailyRoutine updateDailyRoutine(Long userId, Long dailyRoutineId, String name, String description, Day day) throws DiferentDailyRoutinesSameDayException, PermissionException, InstanceNotFoundException;


    Workout findWorkoutById(Long workoutId) throws InstanceNotFoundException;

    List<Workout> findAllWorkoutsFromDailyRoutine(Long dailyRoutineId) throws InstanceNotFoundException, DailyRoutineWithoutWorkoutException;

    Workout addWorkoutToDailyRoutine(Long userId, int targetSets, int targetReps, float targetKg, Long exerciseId, Long dailyRoutineId) throws InstanceNotFoundException, PermissionException;

    Workout deleteWorkoutFromDailyRoutine(Long userId, Long workoutId) throws PermissionException, InstanceNotFoundException;

    Workout updateWorkout(Long userId, Long workoutId, int targetSets, int targetReps, float targetKg) throws PermissionException, InstanceNotFoundException;


//TODO
    void addExistingRoutine(Long userId, Long routineId);

    void deleteFullRoutine(Long userId, Long routineId);

}

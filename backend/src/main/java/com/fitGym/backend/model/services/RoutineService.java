package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.DailyRoutine;
import com.fitGym.backend.model.entities.Exercise;
import com.fitGym.backend.model.entities.Routine;
import com.fitGym.backend.model.entities.Workout;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;
import com.fitGym.backend.model.utils.Block;

import java.util.Date;
import java.util.List;

public interface RoutineService  {


    List<Routine> findAllRoutinesUser(Long userId);

    Routine findRoutineById(Long routineId, Long userId ) throws InstanceNotFoundException, PermissionException;

    Routine addNewRoutine(Long userId, String name, String description, String type, Date startDate, Date endDate, Boolean visibility) throws InstanceNotFoundException;

    Routine updateRoutine(Long userId, Long routineId, String name, String description, String type, Date startDate, Date endDate, Boolean visibility) throws InstanceNotFoundException, PermissionException;

    Routine deleteRoutine(Long userId, Long routineId) throws InstanceNotFoundException, PermissionException;


    DailyRoutine findDailyRoutineById( Long dailyRoutineId) throws InstanceNotFoundException, PermissionException;

    DailyRoutine addDailyRoutineToRoutine(Long userId, Long routineId, String name, String description) throws PermissionException, InstanceNotFoundException;

    DailyRoutine deleteDailyRoutineFromRoutine(Long userId, Long dailyRoutineId) throws PermissionException, InstanceNotFoundException;

    DailyRoutine updateDailyRoutine(Long userId, Long dailyRoutineId, String name, String description) throws PermissionException, InstanceNotFoundException;


    Workout findWorkoutById(Long workoutId) throws InstanceNotFoundException;

    Workout addWorkoutToDailyRoutine(Long userId, int targetSets, int targetReps, float targetKg, Long exerciseId, Long dailyRoutineId) throws InstanceNotFoundException, PermissionException;

    Workout deleteWorkoutFromDailyRoutine(Long userId, Long workoutId) throws PermissionException, InstanceNotFoundException;

    Workout updateWorkout(Long userId, Long workoutId, int targetSets, int targetReps, float targetKg) throws PermissionException, InstanceNotFoundException;



    //DESARROLLAR EN UN FUTURO
    Block<Routine> findAllRoutines(int page, int size);

    void addExistingRoutine(Long userId, Long routineId);


}

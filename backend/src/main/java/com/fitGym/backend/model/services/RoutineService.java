package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.DailyRoutine;
import com.fitGym.backend.model.entities.Exercise;
import com.fitGym.backend.model.entities.Routine;
import com.fitGym.backend.model.entities.Workout;
import com.fitGym.backend.model.utils.Block;

import java.util.List;

public interface RoutineService {


    List<Routine> findAllRoutinesUser(Long userId);

    Routine findRoutineById(Long id);

    void addNewRoutine(Long userId, Routine routine);


    DailyRoutine findDailyRoutineById(Long userId, Long dailyRoutineId);

    void addDailyRoutineToRoutine(Long userId, Long routineId, DailyRoutine dailyRoutine);

    void deleteDailyRoutineFromRoutine(Long userId, Long dailyRoutineId);

    void updateDailyRoutine(Long userId, Long dailyRoutineId, DailyRoutine dailyRoutine);


    Workout findWorkoutById(Long userId, Long DailyRoutineId, Long workoutId);
    void addWorkoutToDailyRoutine(Long userId, Long DailyRoutineId,  Workout workout);

    void deleteWorkoutFromDailyRoutine(Long userId, Long DailyRoutineId, Long workoutId);

    void updateWorkout(Long userId, Long DailyRoutineId, Workout workout);

    //DESARROLLAR EN UN FUTURO
    Block<Routine> findAllRoutines(int page, int size);

    void addExistingRoutine(Long userId, Long routineId);


}

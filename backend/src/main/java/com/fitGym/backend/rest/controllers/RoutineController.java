package com.fitGym.backend.rest.controllers;


import com.fitGym.backend.model.entities.Routine;
import com.fitGym.backend.model.exceptions.DailyRoutineWithoutWorkoutException;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;
import com.fitGym.backend.model.exceptions.RoutineWithoutDailyRoutineException;
import com.fitGym.backend.model.services.RoutineService;
import com.fitGym.backend.model.utils.Block;
import com.fitGym.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.fitGym.backend.rest.dtos.RoutineConversor.toRoutineDto;
import static com.fitGym.backend.rest.dtos.RoutineConversor.toRoutinesDtos;

@RestController
@RequestMapping("/routines")
public class RoutineController {

    //TODO: Meter handler para las excepciones

    @Autowired
    private RoutineService routineService;

    //TODO: Desarrollar metodo con permisos
    @GetMapping("/routines")
    public List<RoutineDto> findAllRoutines() {
        return toRoutinesDtos(routineService.findAllRoutines());
    }
    @GetMapping("/routines/{userId}")
    public List<RoutineDto> findAllRoutinesUser(
            @PathVariable("userId") Long userId
    ){
        return toRoutinesDtos(routineService.findAllRoutinesUser(userId));
    }

    @GetMapping("/routines/{name}")
    public BlockDto<RoutineDto> findRoutinesByNameAndUserId(
            @PathVariable("name") String name,
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Block<Routine> routinesBlock = routineService.findRoutineByName(name, userId, page, size);

        return new BlockDto<>( toRoutinesDtos(routinesBlock.getItems()), routinesBlock.getExistMoreItems());
    }

    //TODO: Desarrollar metodo
    //@GetMapping("/routines/{name}")
    //public RoutineDto findRoutineByName(
    //        @PathVariable("name") String name,
    //        @RequestParam("userId") Long userId
    //){
    //    return toRoutineDto(routineService.findRoutineByName(name, userId));
    //}

    @GetMapping("/routines/{routineId}")
    public RoutineDto findRoutineById(
            @PathVariable("routineId") Long routineId,
            @RequestParam Long userId
    ) throws PermissionException, InstanceNotFoundException {
        return toRoutineDto(routineService.findRoutineById(routineId, userId));
    }

    @PostMapping("/routines/add")
    public Long addRoutine(
            @RequestBody RoutineDto routineDto
    ) throws InstanceNotFoundException {
        return routineService.addNewRoutine(routineDto.getUserId(), routineDto.getName(), routineDto.getDescription(), routineDto.getType(), new Date(), null, routineDto.isVisibility()).getId();
    }

    @PutMapping("/routines/{routineId}")
    public RoutineDto updateRoutine(
            @RequestBody RoutineDto routineDto
    ) throws InstanceNotFoundException, PermissionException {
        return toRoutineDto(routineService.updateRoutine(routineDto.getUserId(), routineDto.getId(), routineDto.getName(), routineDto.getDescription(), routineDto.getType(), new Date(), null, routineDto.isVisibility()));
    }

    @DeleteMapping("/routines/{routineId}")
    public RoutineDto deleteRoutine(
            @PathVariable Long routineId,
            @RequestParam Long userId
    ) throws PermissionException, InstanceNotFoundException {
        return toRoutineDto(routineService.deleteRoutine(userId, routineId));
    }


    @GetMapping("/routines/{routineId}/dailyRoutines")
    public List<DailyRoutineDto> findAllDailyRoutinesFromRoutine(
            @PathVariable Long routineId
    ) throws InstanceNotFoundException, RoutineWithoutDailyRoutineException {
        return DailyRoutineConversor.toDailyRoutineDtos(routineService.findAllDailyRoutinesFromRoutine(routineId));
    }

    @GetMapping("/dailyRoutines/{dailyRoutineId}")
    public DailyRoutineDto findDailyRoutineById(
            @PathVariable Long dailyRoutineId
    ) throws InstanceNotFoundException, PermissionException {
        return DailyRoutineConversor.toDailyRoutineDto(routineService.findDailyRoutineById(dailyRoutineId));
    }

    @PostMapping("/dailyRoutines/add")
    public Long addDailyRoutineToRoutine(
            @RequestParam Long userId,
            @RequestBody DailyRoutineDto dailyRoutineDto
    ) throws InstanceNotFoundException, PermissionException {
        return DailyRoutineConversor.toDailyRoutineDto(routineService.addDailyRoutineToRoutine(userId, dailyRoutineDto.getRoutineId(), dailyRoutineDto.getName(), dailyRoutineDto.getDescription(), dailyRoutineDto.getDay())).getId();
    }

    @PutMapping("/dailyRoutines/{dailyRoutineId}")
    public DailyRoutineDto updateDailyRoutine(
            @RequestParam Long userId,
            @RequestBody DailyRoutineDto dailyRoutineDto
    ) throws InstanceNotFoundException, PermissionException {
        return DailyRoutineConversor.toDailyRoutineDto(routineService.updateDailyRoutine(userId, dailyRoutineDto.getId(), dailyRoutineDto.getName(), dailyRoutineDto.getDescription(), dailyRoutineDto.getDay()));
    }

    @DeleteMapping("/dailyRoutines/{dailyRoutineId}")
    public DailyRoutineDto deleteDailyRoutineFromRoutine(
            @PathVariable Long dailyRoutineId,
            @RequestParam Long userId
    ) throws InstanceNotFoundException, PermissionException {
        return DailyRoutineConversor.toDailyRoutineDto(routineService.deleteDailyRoutineFromRoutine(userId, dailyRoutineId));
    }

    @GetMapping("/dailyRoutines/{dailyRoutineId}/workouts")
    public List<WorkoutDto> findAllWorkoutsFromDailyRoutine(
            @PathVariable Long dailyRoutineId
    ) throws InstanceNotFoundException, DailyRoutineWithoutWorkoutException {
        return WorkoutConversor.toWorkoutDtos(routineService.findAllWorkoutsFromDailyRoutine(dailyRoutineId));
    }

    @GetMapping("/workouts/{workoutId}")
    public WorkoutDto findWorkoutById(
            @PathVariable Long workoutId
    ) throws InstanceNotFoundException {
        return WorkoutConversor.toWorkoutDto(routineService.findWorkoutById(workoutId));
    }

    @PostMapping("/workouts/add")
    public Long addWorkoutToDailyRoutine(
            @RequestParam Long userId,
            @RequestBody WorkoutDto workoutDto
    ) throws InstanceNotFoundException, PermissionException {
        return WorkoutConversor.toWorkoutDto(routineService.addWorkoutToDailyRoutine(userId, workoutDto.getTargetSets(), workoutDto.getTargetReps(), workoutDto.getTargetKg(), workoutDto.getExerciseId(), workoutDto.getDailyRoutineId())).getId();
    }

    @PutMapping("/workouts/{workoutId}")
    public WorkoutDto updateWorkout(
            @RequestParam Long userId,
            @RequestBody WorkoutDto workoutDto
    ) throws InstanceNotFoundException, PermissionException {
        return WorkoutConversor.toWorkoutDto(routineService.updateWorkout(userId, workoutDto.getId(), workoutDto.getTargetSets(), workoutDto.getTargetReps(), workoutDto.getTargetKg()));
    }

    @DeleteMapping("/workouts/{workoutId}")
    public WorkoutDto deleteWorkoutFromDailyRoutine(
            @PathVariable Long workoutId,
            @RequestParam Long userId
    ) throws InstanceNotFoundException, PermissionException {
        return WorkoutConversor.toWorkoutDto(routineService.deleteWorkoutFromDailyRoutine(userId, workoutId));
    }












}

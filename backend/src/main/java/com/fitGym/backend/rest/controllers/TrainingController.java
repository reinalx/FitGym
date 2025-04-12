package com.fitGym.backend.rest.controllers;

import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;
import com.fitGym.backend.model.exceptions.WorkoutWithoutSetsException;
import com.fitGym.backend.model.services.TrainingService;
import com.fitGym.backend.rest.dtos.SetsConversor;
import com.fitGym.backend.rest.dtos.SetsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @GetMapping("/sets/{id}")
    public SetsDto findSetsById(@PathVariable("id") Long id) throws InstanceNotFoundException {
        return SetsConversor.toSetsDto(trainingService.findSetsById(id));
    }

    @GetMapping("/workouts/{id}/sets")
    public List<SetsDto> findAllSetsFromWorkout(@PathVariable("id") Long id) throws InstanceNotFoundException, WorkoutWithoutSetsException {
        return SetsConversor.toSetsDtos(trainingService.findAllSetsFromWorkout(id));
    }

    @PostMapping("/sets/add")
    public SetsDto addSets(
            @RequestParam Long userId,
            @RequestBody SetsDto setsDto
    ) throws InstanceNotFoundException, PermissionException {
        return SetsConversor.toSetsDto(trainingService.addSets(userId, setsDto.getWorkoutId(), setsDto.getReps(), setsDto.getKg()));
    }

    @DeleteMapping("/sets/{id}")
    public SetsDto removeSets(
            @PathVariable Long id,
            @RequestParam Long userId
    ) throws InstanceNotFoundException, PermissionException {
        return SetsConversor.toSetsDto(trainingService.removeSets(userId, id));
    }

    @PutMapping("/sets/{id}")
    public SetsDto updateSets(
            @RequestBody SetsDto setsDto
    ) throws InstanceNotFoundException, PermissionException {
        return SetsConversor.toSetsDto(trainingService.updateSets(setsDto.getId(), setsDto.getId(), setsDto.getReps(), setsDto.getKg()));
    }

}

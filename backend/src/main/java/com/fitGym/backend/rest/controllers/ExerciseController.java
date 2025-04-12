package com.fitGym.backend.rest.controllers;


import com.fitGym.backend.model.entities.Exercise;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;
import com.fitGym.backend.model.services.ExerciseService;
import com.fitGym.backend.model.utils.Block;
import com.fitGym.backend.rest.dtos.BlockDto;
import com.fitGym.backend.rest.dtos.ExerciseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.fitGym.backend.rest.dtos.ExerciseConversor.toExerciseDto;
import static com.fitGym.backend.rest.dtos.ExerciseConversor.toExerciseDtos;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/exercises")
    public BlockDto<ExerciseDto> findExercises(
            @RequestParam Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String muscleTarget,
            @RequestParam(required = false) String muscleGroup,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Block<Exercise> exercisesBlock = exerciseService.findExercises(userId, name, muscleTarget, muscleGroup, page, size);

        return new BlockDto<>(toExerciseDtos(exercisesBlock.getItems()), exercisesBlock.getExistMoreItems());
    }

    @GetMapping("/exercises/{exerciseId}")
    public ExerciseDto findExerciseById(@PathVariable Long exerciseId, @RequestParam Long userId) throws PermissionException, InstanceNotFoundException {
        return toExerciseDto(exerciseService.findExerciseById(userId, exerciseId));
    }

    @PostMapping ("/exercises/add")
    public Long addExercise(
             @RequestBody ExerciseDto exerciseDto
    ) throws InstanceNotFoundException {
        return exerciseService.addExercise(exerciseDto.getUserId(), exerciseDto.getName(), exerciseDto.getDescription(), exerciseDto.getMuscleTarget(), exerciseDto.getMuscleGroup(), exerciseDto.getPicture()).getId();
    }

    @DeleteMapping("/exercises/{exerciseId}")
    public ExerciseDto deleteExercise(@PathVariable Long exerciseId, @RequestParam Long userId) throws PermissionException, InstanceNotFoundException {
        return toExerciseDto(exerciseService.deleteExercise(userId, exerciseId));
    }

    @PutMapping("/exercises/{exerciseId}")
    public ExerciseDto updateExercise(
            @RequestBody ExerciseDto exerciseDto
    ) throws InstanceNotFoundException, PermissionException {
        return toExerciseDto(exerciseService.updateExercise(exerciseDto.getUserId(), exerciseDto.getId(), exerciseDto.getName(), exerciseDto.getDescription(), exerciseDto.getMuscleTarget(), exerciseDto.getMuscleGroup(), exerciseDto.getPicture()));
    }

}

package com.fitGym.backend.rest.dtos;

import com.fitGym.backend.model.entities.Routine;

import java.util.List;
import java.util.stream.Collectors;

import static com.fitGym.backend.rest.dtos.DailyRoutineConversor.*;

public class RoutineConversor {

    private RoutineConversor() {}

    public static RoutineDto toRoutineDto(Routine routine) {

        List<DailyRoutineDto> dailyRoutines =
                routine.getDailyRoutines().stream().map(i -> toDailyRoutineDto(i)).collect(Collectors.toList());

        return new RoutineDto(routine.getId(), routine.getName(), routine.getDescription(), routine.getType(), dailyRoutines, routine.isVisibility(), routine.getUser().getId());
    }

    public static List<RoutineDto> toRoutinesDtos(List<Routine> routines) {
        return routines.stream().map(i -> toRoutineDto(i)).collect(Collectors.toList());
    }
}

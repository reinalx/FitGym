package com.fitGym.backend.rest.dtos;

import com.fitGym.backend.model.entities.Sets;

import java.util.List;
import java.util.stream.Collectors;

public class SetsConversor {
    private SetsConversor() {
    }

    public static SetsDto toSetsDto(Sets sets) {
        return new SetsDto(sets.getId(), sets.getNumberSet(), sets.getReps(), sets.getKg(), sets.getWorkout().getId());
    }
    public static List<SetsDto> toSetsDtos(List<Sets> sets) {
        return sets.stream().map(i -> toSetsDto(i)).collect(Collectors.toList());
    }
}

package com.fitGym.backend.model.entities;

import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;

public interface RoutineDao extends CrudRepository<Routine, Long> {
    Slice<Routine> findByNameOrderById(String name);
}
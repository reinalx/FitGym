package com.fitGym.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoutineDao extends CrudRepository<Routine, Long> {

    List<Routine> findByUserIdOrderByName(Long userId);

    Slice<Routine> findByNameOrderById(String name);

}
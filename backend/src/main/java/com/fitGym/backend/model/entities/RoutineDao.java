package com.fitGym.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface RoutineDao extends CrudRepository<Routine, Long>, ListPagingAndSortingRepository<Routine, Long> {

    List<Routine> findByUserIdOrderByName(Long userId);

    List<Routine> findByNameLikeAndUserIdOrderByName(String name, Long userId);

}
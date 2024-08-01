package com.fitGym.backend.model.entities;

import org.springframework.data.repository.CrudRepository;

public interface WorkoutDao extends CrudRepository<Workout, Long> {

}
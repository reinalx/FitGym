package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.DailyRoutine;
import com.fitGym.backend.model.entities.Exercise;
import com.fitGym.backend.model.entities.Routine;
import com.fitGym.backend.model.utils.Block;

import java.util.List;

public interface RoutineService {

    void addNewRoutine(Routine routine);

}

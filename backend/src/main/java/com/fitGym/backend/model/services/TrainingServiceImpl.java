package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.*;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private PermissionChecker permissionChecker;



    @Autowired
    private SetsDao setsDao;

    @Override
    public Sets addSets(Long userId, Long workoutId, int numberSet, int reps, float kg) throws InstanceNotFoundException, PermissionException {

        Sets sets = new Sets();
        sets.setNumberSet(numberSet);
        sets.setReps(reps);
        sets.setKg(kg);
        sets.setWorkout(permissionChecker.checkWorkoutExistAndBelongToUser(userId, workoutId));

        setsDao.save(sets);

        return sets;

    }

    @Override
    public Sets removeSets(Long userId, Long setsId) throws InstanceNotFoundException, PermissionException {

        Sets sets = permissionChecker.checkSetsExistAndBelongToUser(userId, setsId);
        setsDao.delete(sets);
        return sets;

    }

    @Override
    public Sets updateSets(Long userId, Long setsId, int reps, float kg) throws InstanceNotFoundException, PermissionException {

        Sets set = permissionChecker.checkSetsExistAndBelongToUser(userId, setsId);

        set.setReps(reps);
        set.setKg(kg);
        setsDao.save(set);

        return set;
    }
}


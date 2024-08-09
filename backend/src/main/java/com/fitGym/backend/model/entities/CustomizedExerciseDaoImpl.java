package com.fitGym.backend.model.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

public class CustomizedExerciseDaoImpl implements CustomizedExerciseDao {

    @PersistenceContext
    private EntityManager entityManager;

    private String[] getTokens(String keywords) {

        if (keywords == null || keywords.length() == 0) {
            return new String[0];
        } else {
            return keywords.split("\\s");
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public Slice<Exercise> find(Long exerciseId, String name, String muscleTarget, String muscleGroup , int page, int size) {

        String[] nameTokens = getTokens(name);
        String[] muscleTargetTokens = getTokens(muscleTarget);
        String[] muscleGroupTokens = getTokens(muscleGroup);

        String queryString = "SELECT e FROM Exercise e";

        if(exerciseId != null || nameTokens.length > 0 || muscleTargetTokens.length > 0 || muscleGroupTokens.length > 0) {
            queryString += " WHERE ";
        }

        if(exerciseId != null) {
            queryString += " e.id = :exerciseId";
        }

        if(nameTokens.length > 0) {
            if(exerciseId != null) {
                queryString += " AND ";
            }

            for(int i = 0; i < nameTokens.length-1; i++) {
                queryString += "LOWER(e.name) LIKE LOWER(:nameTokens" + i + ") AND ";
            }
            queryString += "LOWER(e.name) LIKE LOWER(:nameTokens" + (nameTokens.length-1) + ")";

        }

        if(muscleTargetTokens.length > 0) {
            if(exerciseId != null || nameTokens.length > 0) {
                queryString += " AND ";
            }
            for(int i = 0; i < nameTokens.length-1; i++) {
                queryString += "LOWER(e.muscleTarget) LIKE LOWER(:muscleTargetTokens" + i + ") AND ";
            }
            queryString += "LOWER(e.muscleTarget) LIKE LOWER(:muscleTargetTokens" + (muscleTargetTokens.length-1) + ")";
        }

        if(muscleGroupTokens.length > 0) {
            if(exerciseId != null || nameTokens.length > 0 || muscleTargetTokens.length > 0) {
                queryString += " AND ";
            }
            for(int i = 0; i < nameTokens.length-1; i++) {
                queryString += "LOWER(e.muscleGroup) LIKE LOWER(:muscleGroupTokens" + i + ") AND ";
            }
            queryString += "LOWER(e.muscleGroup) LIKE LOWER(:muscleGroupTokens" + (muscleGroupTokens.length-1) + ")";
        }

        queryString += " ORDER BY e.name";

        Query query = entityManager.createQuery(queryString).setFirstResult(page*size).setMaxResults(size+1);

        if (exerciseId != null) {
            query.setParameter("exerciseId", exerciseId);
        }

        if (nameTokens.length != 0) {
            for (int i = 0; i<nameTokens.length; i++) {
                query.setParameter("nameTokens" + i, '%' + nameTokens[i] + '%');
            }
        }

        if(muscleTargetTokens.length != 0) {
            for (int i = 0; i<muscleTargetTokens.length; i++) {
                query.setParameter("muscleTargetTokens" + i, '%' + muscleTargetTokens[i] + '%');
            }
        }
        if(muscleGroupTokens.length != 0) {
            for (int i = 0; i<muscleGroupTokens.length; i++) {
                query.setParameter("muscleGroupTokens" + i, '%' + muscleGroupTokens[i] + '%');
            }
        }

        List<Exercise> exercises =  query.getResultList();

        boolean hasNext = exercises.size() == (size+1);

        if (hasNext) {
            exercises.remove(exercises.size()-1);
        }

        return new SliceImpl<>(exercises, PageRequest.of(page, size), hasNext);

    }
}

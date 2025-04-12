package com.fitgym.test.model.services;

import com.fitGym.backend.model.entities.*;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;
import com.fitGym.backend.model.services.TrainingService;
import com.fitGym.backend.model.utils.Day;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = com.fitGym.backend.FitgymServerApplication.class)
@ActiveProfiles("test")
@Transactional
public class TrainingServiceTest {

    private final Long NON_EXISTENT_ID = Long.valueOf(-1);

    @Autowired
    private TrainingService trainingService;
    @Autowired
    private RoutineDao routineDao;
    @Autowired
    private DailyRoutineDao dailyRoutineDao;
    @Autowired
    private WorkoutDao workoutDao;
    @Autowired
    private ExerciseDao exerciseDao;
    @Autowired
    private SetsDao setsDao;
    @Autowired
    private UserDao userDao;

    private User createUser(String userName) {
        return new User(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com");
    }
    private Routine createRoutineOfUser(String name, String description, User user) {
        return new Routine(name, description, "type", new Date(), null, true, user);
    }
    private DailyRoutine createDailyRoutineOfRoutine(String name, String description, Day day, Routine routine ) {
        return new DailyRoutine(name, description, day, routine );
    }
    private Workout createWorkoutOfRoutine(int targetSets, int targetReps, float targetKg, Exercise exercise, DailyRoutine dailyRoutine) {
        return new Workout(targetSets, targetReps, targetKg, exercise, dailyRoutine);
    }
    private Exercise createExerciseOfUser(String name, String muscleTarget, String muscleGroup, User user) {
        return new Exercise(name, "description", muscleTarget, muscleGroup, null, user);
    }
    private Sets createSetsOfWorkout(int numberSet, int reps, float kg, Workout workout) {
        return new Sets(numberSet, reps, kg, workout);
    }

    private Workout createWorkoutToTest(){
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);

        DailyRoutine dailyRoutine = createDailyRoutineOfRoutine("dailyRoutine1", "dailyRoutine1", Day.MONDAY, routine);
        dailyRoutineDao.save(dailyRoutine);

        Exercise exercise = createExerciseOfUser("exercise1", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise);

        Workout workout = createWorkoutOfRoutine(10, 10, 10.0f, exercise , dailyRoutine);
        workoutDao.save(workout);

        return workout;
    }

    @Test
    public void testFindSetsById() throws InstanceNotFoundException {
        Workout workout = createWorkoutToTest();
        Sets sets = createSetsOfWorkout(1, 10, 10.0f, workout);
        setsDao.save(sets);

        assertEquals(sets, trainingService.findSetsById(sets.getId()));
    }

    @Test
    public void testFindSetsByIdNotFound() throws InstanceNotFoundException {
        assertThrows(InstanceNotFoundException.class, () -> trainingService.findSetsById(NON_EXISTENT_ID));
    }

    @Test
    public void testAddSets() throws InstanceNotFoundException, PermissionException {

        Workout workout = createWorkoutToTest();
        Long userId = workout.getExercise().getUser().getId();

        Sets sets = trainingService.addSets(userId, workout.getId(), 10, 10.0f);

        assertEquals(sets, trainingService.findSetsById(sets.getId()));
        assertEquals(1, workout.getNumberOfSets());

    }
    @Test
    public void testAddSetsWithoutPermission()  {
        Workout workout = createWorkoutToTest();
        User user = createUser("user2");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        assertThrows(PermissionException.class, () -> trainingService.addSets(user.getId(), workout.getId(), 10, 10.0f));
    }
    @Test
    public void testRemoveSets() throws InstanceNotFoundException, PermissionException {
        Workout workout = createWorkoutToTest();
        Long userId = workout.getExercise().getUser().getId();

        Sets sets = createSetsOfWorkout(1, 10, 10.0f, workout);
        setsDao.save(sets);

        trainingService.removeSets(userId, sets.getId());

        assertEquals(0, workout.getNumberOfSets());
        assertThrows(InstanceNotFoundException.class, () -> trainingService.findSetsById(sets.getId()));
    }

    @Test
    public void testUpdateSets() throws InstanceNotFoundException, PermissionException {
        Workout workout = createWorkoutToTest();
        Long userId = workout.getExercise().getUser().getId();

        Sets sets = createSetsOfWorkout(1, 10, 10.0f, workout);
        setsDao.save(sets);

        Sets updatedSets = trainingService.updateSets(userId, sets.getId(), 20, 20.0f);
        Sets foundSets = trainingService.findSetsById(sets.getId());

        assertEquals(updatedSets, foundSets);
        assertEquals(1, foundSets.getNumberSet());
        assertEquals(20, foundSets.getReps());
        assertEquals(20.0f, foundSets.getKg());
    }
    @Test
    public void testUpdateSetsWithoutPermission()  {
        Workout workout = createWorkoutToTest();
        User user = createUser("user2");
        user.setRole(User.RoleType.USER);
        userDao.save(user);
        Sets sets = createSetsOfWorkout(1, 10, 10.0f, workout);
        setsDao.save(sets);
        assertThrows(PermissionException.class, () -> trainingService.updateSets(user.getId(), sets.getId(), 20, 20.0f));
    }

    @Test
    public void testGetSetsByNumber() throws InstanceNotFoundException, PermissionException {
        Workout workout = createWorkoutToTest();
        Long userId = workout.getExercise().getUser().getId();

        Sets set1 = trainingService.addSets(userId, workout.getId(), 11, 11.0f);
        Sets set2 = trainingService.addSets(userId, workout.getId(), 12, 12.0f);
        Sets set3 = trainingService.addSets(userId, workout.getId(), 13, 13.0f);

        assertEquals(set1, trainingService.findSetsByNumber(workout.getId(), userId, 1));
        assertEquals(set2, trainingService.findSetsByNumber(workout.getId(), userId, 2));
        assertEquals(set3, trainingService.findSetsByNumber(workout.getId(), userId, 3));

        assertThrows(InstanceNotFoundException.class, () -> trainingService.findSetsByNumber(workout.getId(), userId, 4));
    }

    //TODO: Añadir test para que no se puedan borrar sets que no esten en el workout


}
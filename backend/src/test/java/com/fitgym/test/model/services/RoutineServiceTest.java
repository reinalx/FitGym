package com.fitgym.test.model.services;

import com.fitGym.backend.model.entities.*;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;
import com.fitGym.backend.model.exceptions.RoutineWithoutDailyRoutineException;
import com.fitGym.backend.model.services.RoutineService;
import com.fitGym.backend.model.utils.Day;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//TODO: Actualizar test para compronar que se asocia todo correctamente con las rutinas

@SpringBootTest(classes = com.fitGym.backend.FitgymServerApplication.class)
@ActiveProfiles("test")
@Transactional
public class RoutineServiceTest {

    private final Long NON_EXISTENT_ID = (long) -1;

    @Autowired
    private RoutineService routineService;

    @Autowired
    private RoutineDao routineDao;

    @Autowired
    private ExerciseDao exerciseDao;

    @Autowired
    private DailyRoutineDao dailyRoutineDao;

    @Autowired
    private WorkoutDao workoutDao;

    @Autowired
    private UserDao userDao;


    private User createUser(String userName) {
        return new User(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com");
    }
    private Routine createRoutineOfUser(String name, String description, User user) {
        return new Routine(name, description, "type", new Date(), null, true, user);
    }
    private DailyRoutine createDailyRoutineOfRoutine(String name, String description, Day day, Routine routine) {
        return new DailyRoutine(name, description, day ,routine);
    }
    private Workout createWorkoutOfRoutine(int targetSets, int targetReps, float targetKg, Exercise exercise, DailyRoutine dailyRoutine) {
        return new Workout(targetSets, targetReps, targetKg, exercise, dailyRoutine);
    }
    private Exercise createExerciseOfUser(String name, String muscleTarget, String muscleGroup, User user) {
        return new Exercise(name, "description", muscleTarget, muscleGroup, null, user);
    }



    @Test
    public void testFindRoutineById() throws InstanceNotFoundException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);

        assertEquals(routine, routineService.findRoutineById(routine.getId(), null));
    }
    @Test
    public void testFindRoutineByIdNotFound() throws InstanceNotFoundException, PermissionException {
        assertThrows(InstanceNotFoundException.class, () -> routineService.findRoutineById(NON_EXISTENT_ID, null));
    }

    @Test
    public void testFindAllRoutinesUser() {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);
        Routine routine2 = createRoutineOfUser("rutina2", "rutina2", user);
        routineDao.save(routine2);

        assertEquals(Arrays.asList(routine, routine2), routineService.findAllRoutinesUser(user.getId()));
    }

    @Test
    public void testFindRoutineByNameAndUser() {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);
        User user2 = createUser("user2");
        user2.setRole(User.RoleType.USER);
        userDao.save(user2);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);
        Routine routine2 = createRoutineOfUser("rutina2", "rutina2", user);
        routineDao.save(routine2);
        Routine routine3 = createRoutineOfUser("rutina3", "rutina3", user2);
        routineDao.save(routine3);

        assertEquals(Arrays.asList(routine, routine2), routineService.findRoutinesByNameAndUserId("%rutina%", user.getId()));

    }

    @Test
    public void addNewRoutine() throws InstanceNotFoundException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routine = routineService.addNewRoutine(user.getId(), routine.getName(), routine.getDescription(), routine.getType(), routine.getStartDate(), routine.getEndDate(), routine.isVisibility());

        assertEquals(routine, routineDao.findById(routine.getId()).get());
    }
    @Test
    public void updateRoutine() throws InstanceNotFoundException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);

        routineService.updateRoutine(user.getId(), routine.getId(), "update", "update", routine.getType(), routine.getStartDate(), routine.getEndDate(), routine.isVisibility());
        Routine foundRoutine = routineDao.findById(routine.getId()).get();
        assertEquals("update", foundRoutine.getName());
        assertEquals("update", foundRoutine.getDescription());
        assertEquals(routine.getType(), foundRoutine.getType());
        assertEquals(routine.getStartDate(), foundRoutine.getStartDate());
        assertEquals(routine.getEndDate(), foundRoutine.getEndDate());

    }
    @Test
    public void testUpdateRoutineWithoutPermission() {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);

        User user2 = createUser("user2");
        user2.setRole(User.RoleType.USER);
        userDao.save(user2);

        assertThrows(PermissionException.class, () -> routineService.updateRoutine(user2.getId(), routine.getId(), "update", "update", routine.getType(), routine.getStartDate(), routine.getEndDate(), routine.isVisibility()));
    }
    @Test
    public void testDeleteRoutine() throws InstanceNotFoundException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);


        routineService.deleteRoutine(user.getId(), routine.getId());
        assertThrows(InstanceNotFoundException.class, () -> routineService.findRoutineById(routine.getId(), user.getId()));
    }
    @Test
    public void testDeleteRoutineWithoutPermission() {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);
        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);
        User user2 = createUser("user2");
        user2.setRole(User.RoleType.USER);
        userDao.save(user2);
        assertThrows(PermissionException.class, () -> routineService.deleteRoutine(user2.getId(), routine.getId()));
    }

    @Test
    public void testFindDailyRoutineById() throws InstanceNotFoundException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);
        DailyRoutine dailyRoutine = createDailyRoutineOfRoutine("dailyRoutine1", "dailyRoutine1",  Day.MONDAY, routine);
        dailyRoutineDao.save(dailyRoutine);

        assertEquals(dailyRoutine, routineService.findDailyRoutineById(dailyRoutine.getId()));
    }
    @Test
    public void testFindDailyRoutineByIdNotFound()  {
        assertThrows(InstanceNotFoundException.class, () -> routineService.findDailyRoutineById(NON_EXISTENT_ID));
    }

    @Test
    public void addDailyRoutineToRoutine() throws InstanceNotFoundException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);

        DailyRoutine dailyRoutine2 = routineService.addDailyRoutineToRoutine(user.getId(), routine.getId(), "dailyRoutine2", "dailyRoutine2", Day.MONDAY);
        assertEquals(dailyRoutine2, dailyRoutineDao.findById(dailyRoutine2.getId()).get());
    }
    @Test
    public void testAddDailyRoutineToRoutineWithoutPermission()  {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);
        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);
        User user2 = createUser("user2");
        user2.setRole(User.RoleType.USER);
        userDao.save(user2);
        assertThrows(PermissionException.class, () -> routineService.addDailyRoutineToRoutine(user2.getId(), routine.getId(), "dailyRoutine2", "dailyRoutine2", Day.MONDAY));
    }

    @Test
    public void updateDailyRoutine() throws InstanceNotFoundException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);
        DailyRoutine dailyRoutine = createDailyRoutineOfRoutine("dailyRoutine1", "dailyRoutine1", Day.MONDAY, routine);
        routine.addDailyRoutine(dailyRoutine);
        dailyRoutineDao.save(dailyRoutine);

        dailyRoutine = routineService.updateDailyRoutine(user.getId(), dailyRoutine.getId(), "dailyRoutine2", "dailyRoutine2", Day.FRIDAY);
        DailyRoutine foundDailyRoutine = dailyRoutineDao.findById(dailyRoutine.getId()).get();
        assertEquals("dailyRoutine2", foundDailyRoutine.getName());
        assertEquals("dailyRoutine2", foundDailyRoutine.getDescription());
        assertEquals(routine, foundDailyRoutine.getRoutine());

    }
    @Test
    public void testUpdateDailyRoutineWithoutPermission()  {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);
        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);
        User user2 = createUser("user2");
        user2.setRole(User.RoleType.USER);
        userDao.save(user2);
        DailyRoutine dailyRoutine = createDailyRoutineOfRoutine("dailyRoutine1", "dailyRoutine1", Day.TUESDAY,  routine);
        dailyRoutineDao.save(dailyRoutine);
        assertThrows(PermissionException.class, () -> routineService.updateDailyRoutine(user2.getId(), dailyRoutine.getId(), "dailyRoutine2", "dailyRoutine2", Day.MONDAY));
    }

    @Test
    public void testDeleteDailyRoutineFromRoutine() throws InstanceNotFoundException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);

        DailyRoutine dailyRoutine = createDailyRoutineOfRoutine("dailyRoutine1", "dailyRoutine1", Day.MONDAY, routine);
        dailyRoutineDao.save(dailyRoutine);


        routineService.deleteDailyRoutineFromRoutine(user.getId(),  dailyRoutine.getId());
        assertThrows(InstanceNotFoundException.class, () -> routineService.findDailyRoutineById( dailyRoutine.getId()));
    }
    @Test
    public void testDeleteDailyRoutineFromRoutineWithoutPermission()  {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);
        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);
        User user2 = createUser("user2");
        user2.setRole(User.RoleType.USER);
        userDao.save(user2);
        DailyRoutine dailyRoutine = createDailyRoutineOfRoutine("dailyRoutine1", "dailyRoutine1", Day.MONDAY, routine);
        dailyRoutineDao.save(dailyRoutine);
        assertThrows(PermissionException.class, () -> routineService.deleteDailyRoutineFromRoutine(user2.getId(),  dailyRoutine.getId()));
    }

    @Test
    public void testFindWorkoutById() throws InstanceNotFoundException {
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

        assertEquals(workout, routineService.findWorkoutById(workout.getId()));
    }
    @Test
    public void testFindWorkoutByIdNotFound()  {
        assertThrows(InstanceNotFoundException.class, () -> routineService.findWorkoutById(NON_EXISTENT_ID));
    }

    @Test
    public void testAddWorkoutToDailyRoutine() throws InstanceNotFoundException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);

        DailyRoutine dailyRoutine = createDailyRoutineOfRoutine("dailyRoutine1", "dailyRoutine1", Day.MONDAY, routine);
        dailyRoutineDao.save(dailyRoutine);

        Exercise exercise = createExerciseOfUser("exercise1", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise);

        Workout workout = routineService.addWorkoutToDailyRoutine(user.getId(), 10, 10, 10.0f, exercise.getId(), dailyRoutine.getId());

        assertEquals(workout, workoutDao.findById(workout.getId()).get());
    }
    @Test
    public void testAddWorkoutToDailyRoutineWithoutPermission()  {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);
        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);
        User user2 = createUser("user2");
        user2.setRole(User.RoleType.USER);
        userDao.save(user2);
        DailyRoutine dailyRoutine = createDailyRoutineOfRoutine("dailyRoutine1", "dailyRoutine1", Day.MONDAY, routine);
        dailyRoutineDao.save(dailyRoutine);
        Exercise exercise = createExerciseOfUser("exercise1", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise);
        assertThrows(PermissionException.class, () -> routineService.addWorkoutToDailyRoutine(user2.getId(), 10, 10, 10.0f, exercise.getId(), dailyRoutine.getId()));
    }

    @Test
    public void testDeleteWorkoutFromDailyRoutine() throws InstanceNotFoundException, PermissionException {
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

        routineService.deleteWorkoutFromDailyRoutine(user.getId(), workout.getId());

        assertThrows(InstanceNotFoundException.class, () -> routineService.findWorkoutById(workout.getId()));
    }
    @Test
    public void testDeleteWorkoutFromDailyRoutineWithoutPermission()  {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);
        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);
        User user2 = createUser("user2");
        user2.setRole(User.RoleType.USER);
        userDao.save(user2);
        DailyRoutine dailyRoutine = createDailyRoutineOfRoutine("dailyRoutine1", "dailyRoutine1", Day.MONDAY, routine);
        dailyRoutineDao.save(dailyRoutine);
        Exercise exercise = createExerciseOfUser("exercise1", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise);
        Workout workout = createWorkoutOfRoutine(10, 10, 10.0f, exercise , dailyRoutine);
        workoutDao.save(workout);
        assertThrows(PermissionException.class, () -> routineService.deleteWorkoutFromDailyRoutine(user2.getId(), workout.getId()));
    }

    @Test
    public void testUpdateWorkout() throws InstanceNotFoundException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);

        DailyRoutine dailyRoutine = createDailyRoutineOfRoutine("dailyRoutine1", "dailyRoutine1",  Day.MONDAY, routine);
        dailyRoutineDao.save(dailyRoutine);

        Exercise exercise = createExerciseOfUser("exercise1", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise);

        Workout workout = createWorkoutOfRoutine(10, 10, 10.0f, exercise , dailyRoutine);
        workoutDao.save(workout);

        workout = routineService.updateWorkout(user.getId(), workout.getId(), 20, 20, 20.0f);

        assertEquals(workout.getTargetSets(), 20);
        assertEquals(workout.getTargetReps(), 20);
        assertEquals(workout.getTargetKg(), 20.0f);
    }
    @Test
    public void testUpdateWorkoutWithoutPermission()  {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);
        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);
        User user2 = createUser("user2");
        user2.setRole(User.RoleType.USER);
        userDao.save(user2);
        DailyRoutine dailyRoutine = createDailyRoutineOfRoutine("dailyRoutine1", "dailyRoutine1",  Day.MONDAY,  routine);
        dailyRoutineDao.save(dailyRoutine);
        Exercise exercise = createExerciseOfUser("exercise1", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise);
        Workout workout = createWorkoutOfRoutine(10, 10, 10.0f, exercise , dailyRoutine);
        workoutDao.save(workout);
        assertThrows(PermissionException.class, () -> routineService.updateWorkout(user2.getId(), workout.getId(), 20, 20, 20.0f));
    }

    @Test
    public void testFindAllDailyRoutinesFromRoutine() throws InstanceNotFoundException, RoutineWithoutDailyRoutineException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);

        DailyRoutine dailyRoutine = routineService.addDailyRoutineToRoutine(user.getId(), routine.getId(), "name1", "description1", Day.MONDAY);
        DailyRoutine dailyRoutine2 = routineService.addDailyRoutineToRoutine(user.getId(), routine.getId(), "name2", "description2", Day.TUESDAY);
        DailyRoutine dailyRoutine3 = routineService.addDailyRoutineToRoutine(user.getId(), routine.getId(), "name3", "description3", Day.THURSDAY);

        assertEquals(Arrays.asList(dailyRoutine, dailyRoutine2, dailyRoutine3), routineService.findAllDailyRoutinesFromRoutine(routine.getId()));

    }

    @Test
    public void testFindAllDailyRoutinesFromRoutineNoResult() {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Routine routine = createRoutineOfUser("rutina1", "rutina1", user);
        routineDao.save(routine);

        assertThrows(RoutineWithoutDailyRoutineException.class, () -> routineService.findAllDailyRoutinesFromRoutine(routine.getId()));
    }

    @Test
    public void testFindAllDailyRoutinesFromRoutineNotFound() {
        assertThrows(InstanceNotFoundException.class, () -> routineService.findAllDailyRoutinesFromRoutine(NON_EXISTENT_ID));
    }


    //TODO: hacer pruebas de los metodos introducidos, findAllDailyRoutinesFromRoutine, findAllWorkoutsFromRoutine

}
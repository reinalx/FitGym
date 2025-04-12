package com.fitgym.test.model.services;

import com.fitGym.backend.model.entities.Exercise;
import com.fitGym.backend.model.entities.ExerciseDao;
import com.fitGym.backend.model.entities.User;
import com.fitGym.backend.model.entities.UserDao;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.PermissionException;
import com.fitGym.backend.model.services.ExerciseService;
import com.fitGym.backend.model.utils.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = com.fitGym.backend.FitgymServerApplication.class)
@ActiveProfiles("test")
@Transactional
public class ExerciseServiceTest {

    private final Long NON_EXISTENT_ID = Long.valueOf(-1);

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseDao exerciseDao;

    @Autowired
    private UserDao userDao;


    private Exercise createExercise(String name, String muscleTarget, String muscleGroup) {
        return new Exercise(name, "description", muscleTarget, muscleGroup, null, null);
    }
    private Exercise createExerciseOfUser(String name, String muscleTarget, String muscleGroup, User user) {
        return new Exercise(name, "description", muscleTarget, muscleGroup, null, user);
    }
    private User createUser(String userName) {
        return new User(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com");
    }


    @Test
    public void testFindExerciseById() throws InstanceNotFoundException, PermissionException {
        Exercise exercise= createExercise("exercise1", "muscleTarget", "muscleGroup");
        exerciseDao.save(exercise);

        assertEquals(exercise, exerciseService.findExerciseById(null , exercise.getId()));
    }

    @Test
    public void testFindExerciseByIdOfUser() throws InstanceNotFoundException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);
        Exercise exercise = createExerciseOfUser("exercise1", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise);

        assertEquals(exercise, exerciseService.findExerciseById(user.getId(), exercise.getId()));
    }


    @Test
    public void testFindNoExercisesById(){
        assertThrows(InstanceNotFoundException.class, () -> exerciseService.findExerciseById(null, NON_EXISTENT_ID));
    }

    @Test
    public void testFindAllExercises() throws InstanceNotFoundException, PermissionException {
        Exercise exercise1 = createExercise("exercise1", "muscleTarget", "muscleGroup");
        exerciseDao.save(exercise1);
        Exercise exercise2 = createExercise("exercise2", "muscleTarget", "muscleGroup");
        exerciseDao.save(exercise2);
        Exercise exercise3 = createExercise("exercise3", "muscleTarget", "muscleGroup");
        exerciseDao.save(exercise3);

        Block<Exercise> expectedBlock = new Block<>(Arrays.asList(exercise1, exercise2, exercise3), false);

        assertEquals(expectedBlock, exerciseService.findExercises(null, null, null, null, 0, 3));
        assertEquals(expectedBlock, exerciseService.findExercises(null, "", null, null, 0, 3));
        assertEquals(expectedBlock, exerciseService.findExercises(null, "", "", null, 0, 3));
        assertEquals(expectedBlock, exerciseService.findExercises(null, "", "", "", 0, 3));


    }

    @Test
    public void testFindExerciseByPages() throws InstanceNotFoundException, PermissionException {
        Exercise exercise1 = createExercise("exercise1", "muscleTarget", "muscleGroup");
        exerciseDao.save(exercise1);
        Exercise exercise2 = createExercise("exercise2", "muscleTarget", "muscleGroup");
        exerciseDao.save(exercise2);
        Exercise exercise3 = createExercise("exercise3", "muscleTarget", "muscleGroup");
        exerciseDao.save(exercise3);

        Block<Exercise> expectedBlock = new Block<>(Arrays.asList(exercise1, exercise2), true);
        assertEquals(expectedBlock, exerciseService.findExercises(null, null, null, null, 0, 2));

        expectedBlock = new Block<>(Arrays.asList(exercise3), false);
        assertEquals(expectedBlock, exerciseService.findExercises(null, null, null, null, 1, 2));

        expectedBlock = new Block<>(Arrays.asList(), false);
        assertEquals(expectedBlock, exerciseService.findExercises(null, null, null, null, 2, 2));

    }

    @Test
    public void testFindByName() throws InstanceNotFoundException, PermissionException {
        Exercise exercise1 = createExercise("exercise1", "muscleTarget", "muscleGroup");
        exerciseDao.save(exercise1);
        Exercise exercise2 = createExercise("exercise2", "muscleTarget", "muscleGroup");
        exerciseDao.save(exercise2);
        Exercise exercise3 = createExercise("nothing", "muscleTarget", "muscleGroup");
        exerciseDao.save(exercise3);

        Block<Exercise> expectedBlock = new Block<>(Arrays.asList(exercise1, exercise2), false);

        assertEquals(expectedBlock, exerciseService.findExercises(null, "exercise", null, null, 0, 2));
    }

    @Test
    public void testFindByMuscleTarget() throws InstanceNotFoundException, PermissionException {
        Exercise exercise1 = createExercise("exercise1", "chest", "muscleGroup");
        exerciseDao.save(exercise1);
        Exercise exercise2 = createExercise("exercise2", "muscleTarget", "muscleGroup");
        exerciseDao.save(exercise2);
        Exercise exercise3 = createExercise("exercise3", "chest", "muscleGroup");
        exerciseDao.save(exercise3);

        Block<Exercise> expectedBlock = new Block<>(Arrays.asList(exercise1, exercise3), false);

        assertEquals(expectedBlock, exerciseService.findExercises(null, null, "ches", null, 0, 2));
    }

    @Test
    public void testFindByMuscleGroup() throws InstanceNotFoundException, PermissionException {
        Exercise exercise1 = createExercise("exercise1", "muscleTarget", "muscleGroup");
        exerciseDao.save(exercise1);
        Exercise exercise2 = createExercise("exercise2", "muscleTarget", "chest");
        exerciseDao.save(exercise2);
        Exercise exercise3 = createExercise("exercise3", "muscleTarget", "chest");
        exerciseDao.save(exercise3);

        Block<Exercise> expectedBlock = new Block<>(Arrays.asList(exercise2, exercise3), false);

        assertEquals(expectedBlock, exerciseService.findExercises(null, null, null, "chest", 0, 2));
    }

    @Test
    public void testFindByUser() throws InstanceNotFoundException, PermissionException {

        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Exercise exercise1 = createExerciseOfUser("exercise1", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise1);
        Exercise exercise2 = createExerciseOfUser("exercise2", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise2);
        Exercise exercise3 = createExerciseOfUser("exercise3", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise3);

        Block<Exercise> expectedBlock = new Block<>(Arrays.asList(exercise1, exercise2, exercise3), false);
        Block<Exercise> foundBlock = exerciseService.findExercises(user.getId(), null, null, null, 0, 3);

        assertEquals(expectedBlock,foundBlock );
    }

    @Test
    public void testFindByAllCriteria() throws InstanceNotFoundException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Exercise exercise1 = createExerciseOfUser("exercise1", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise1);
        Exercise exercise2 = createExerciseOfUser("exercise2", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise2);
        Exercise exercise3 = createExerciseOfUser("exercise3", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise3);

        Block<Exercise> expectedBlock = new Block<>(Arrays.asList(exercise1, exercise2, exercise3), false);

        assertEquals(expectedBlock, exerciseService.findExercises(user.getId(), "exercise", "muscle", "muscle", 0, 3));
    }

    @Test
    public void testFindNoExercises() throws InstanceNotFoundException, PermissionException {
        Exercise exercise1 = createExercise("exercise1", "muscleTarget", "muscleGroup");
        exerciseDao.save(exercise1);
        Exercise exercise2 = createExercise("exercise2", "muscleTarget", "chest");
        exerciseDao.save(exercise2);
        Exercise exercise3 = createExercise("exercise3", "muscleTarget", "chest");
        exerciseDao.save(exercise3);

        Block<Exercise> expectedBlock = new Block<>(Arrays.asList(), false);

        assertEquals(expectedBlock, exerciseService.findExercises(null, "nothing", null, null, 0, 2));
    }

    @Test
    public void testAddExercise() throws InstanceNotFoundException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Exercise exercise = createExerciseOfUser("exercise1", "muscleTarget", "muscleGroup", user);
        exercise = exerciseService.addExercise(user.getId(), exercise.getName(), exercise.getDescription(), exercise.getMuscleTarget(), exercise.getMuscleGroup(), null);

        Exercise foundExercise = exerciseDao.findById(exercise.getId()).get();
        assertEquals(exercise, foundExercise );
    }

    @Test
    public void testUpdateExercise() throws InstanceNotFoundException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Exercise exercise = createExerciseOfUser("exercise1", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise);

        exerciseService.updateExercise(user.getId(), exercise.getId(), "anotherName", exercise.getDescription(), "anotherMuscle", exercise.getMuscleGroup(), null );

        Exercise foundExercise = exerciseDao.findById(exercise.getId()).get();
        assertEquals(exercise.getId(), foundExercise.getId());
        assertEquals("anotherName", foundExercise.getName());
        assertEquals(exercise.getDescription(), foundExercise.getDescription());
        assertEquals("anotherMuscle", foundExercise.getMuscleTarget());
        assertEquals(exercise.getMuscleGroup(), foundExercise.getMuscleGroup());
        assertEquals(exercise.getUser(), foundExercise.getUser());
    }
    @Test
    public void testUpdateExerciseWithoutPermission()  {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);
        Exercise exercise = createExerciseOfUser("exercise1", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise);
        User user2 = createUser("user2");
        user2.setRole(User.RoleType.USER);
        userDao.save(user2);
        assertThrows(PermissionException.class, () -> exerciseService.updateExercise(user2.getId(), exercise.getId(), "anotherName", exercise.getDescription(), "anotherMuscle", exercise.getMuscleGroup(), null ));
    }

    @Test
    public void testDeleteExercise() throws InstanceNotFoundException, PermissionException {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);

        Exercise exercise = createExerciseOfUser("exercise1", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise);

        exerciseService.deleteExercise(user.getId(), exercise.getId());

        assertThrows(InstanceNotFoundException.class, () -> exerciseService.findExerciseById(user.getId(), exercise.getId()));
    }
    @Test
    public void testDeleteExerciseWithoutPermission()  {
        User user = createUser("user1");
        user.setRole(User.RoleType.USER);
        userDao.save(user);
        Exercise exercise = createExerciseOfUser("exercise1", "muscleTarget", "muscleGroup", user);
        exerciseDao.save(exercise);
        User user2 = createUser("user2");
        user2.setRole(User.RoleType.USER);
        userDao.save(user2);
        assertThrows(PermissionException.class, () -> exerciseService.deleteExercise(user2.getId(), exercise.getId()));
    }

}

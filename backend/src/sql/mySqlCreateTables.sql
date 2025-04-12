DROP TABLE IF EXISTS Sets;
DROP TABLE IF EXISTS Workout;
DROP TABLE IF EXISTS DailyRoutine;
DROP TABLE IF EXISTS RoutineUser;
DROP TABLE IF EXISTS Routine;
DROP TABLE IF EXISTS Exercise;
DROP TABLE IF EXISTS Messages;
DROP TABLE IF EXISTS UserFriend;
DROP TABLE IF EXISTS User;


CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL,
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE TABLE UserFriend (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user1Id BIGINT NOT NULL,
    user2Id BIGINT NOT NULL,
    CONSTRAINT UserFriendPK PRIMARY KEY (id),
    CONSTRAINT UserFriendUser1IdFK FOREIGN KEY (user1Id)
        REFERENCES User (id),
    CONSTRAINT UserFriendUser2IdFK FOREIGN KEY (user2Id)
        REFERENCES User (id)
) ENGINE = InnoDB;

CREATE TABLE Messages (
    id BIGINT NOT NULL AUTO_INCREMENT,
    msg VARCHAR(200) NOT NULL,
    date DATETIME NOT NULL,
    toUserId BIGINT NOT NULL,
    fromUserId BIGINT NOT NULL,
    CONSTRAINT MessagesPK PRIMARY KEY (id),
    CONSTRAINT MessagesToUserIdFK FOREIGN KEY (toUserId)
        REFERENCES User (id),
    CONSTRAINT MessagesFromUserIdFK FOREIGN KEY (fromUserId)
        REFERENCES User (id)
)ENGINE = InnoDB;


CREATE TABLE Exercise (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(200) NOT NULL,
    muscleTarget VARCHAR(60) NOT NULL,
    muscleGroup VARCHAR(60) NOT NULL,
    picture VARCHAR(255),
    userId BIGINT,

    CONSTRAINT ExercisePK PRIMARY KEY (id),
    CONSTRAINT ExerciseUserIdFK FOREIGN KEY(userId)
        REFERENCES User (id)
)ENGINE = InnoDB;

CREATE TABLE Routine (
     id BIGINT NOT NULL AUTO_INCREMENT,
     name VARCHAR(60) NOT NULL,
     description VARCHAR(200),
     type VARCHAR(60) NOT NULL,
     startDate DATETIME NOT NULL,
     endDate DATETIME,
     visibility BOOLEAN NOT NULL DEFAULT TRUE,
     userId BIGINT NOT NULL,

     CONSTRAINT RoutinePK PRIMARY KEY (id),
     CONSTRAINT RoutineUserIdFK FOREIGN KEY(userId)
        REFERENCES User (id)
)ENGINE = InnoDB;



CREATE TABLE DailyRoutine (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(200) ,
    day INT NOT NULL,
    routineId BIGINT NOT NULL,
    CONSTRAINT DailyRoutinePK PRIMARY KEY (id),
    CONSTRAINT DailyRoutineRoutineIdFK FOREIGN KEY(routineId)
        REFERENCES Routine (id),
    CONSTRAINT chk_dayOfWeek CHECK (day BETWEEN 0 AND 6)  -- Validación para asegurar que dayOfWeek esté entre 1 y 7

)ENGINE = InnoDB;


CREATE TABLE Workout (
    id BIGINT NOT NULL AUTO_INCREMENT,
    targetSets INTEGER NOT NULL ,
    targetReps INTEGER NOT NULL ,
    targetKg FLOAT NOT NULL,
    exerciseId BIGINT NOT NULL,
    dailyRoutineId BIGINT NOT NULL,
    CONSTRAINT WorkoutPK PRIMARY KEY (id),
    CONSTRAINT WorkoutExerciseIdFK FOREIGN KEY(exerciseId)
            REFERENCES Exercise (id),
    CONSTRAINT WorkoutRoutineIdFK FOREIGN KEY(dailyRoutineId)
            REFERENCES DailyRoutine (id)
)ENGINE = InnoDB;

CREATE TABLE Sets (
    id BIGINT NOT NULL AUTO_INCREMENT,
    numberSet INTEGER NOT NULL DEFAULT 1,
    reps INTEGER NOT NULL,
    kg float NOT NULL ,
    workoutId BIGINT NOT NULL,
    CONSTRAINT SetPK PRIMARY KEY (id),
    CONSTRAINT SetWorkoutIdFK FOREIGN KEY(workoutId)
    REFERENCES Workout (id)

)ENGINE = InnoDB;



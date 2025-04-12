-- Insertar datos en la tabla AppUser
INSERT INTO User (userName, password, firstName, lastName, email, role) VALUES
('jdoe', 'password123', 'John', 'Doe', 'jdoe@example.com', 1),
('asmith', 'password456', 'Alice', 'Smith', 'asmith@example.com', 2),
('bwhite', 'password789', 'Bob', 'White', 'bwhite@example.com', 1);

-- Insertar datos en la tabla UserFriend
INSERT INTO UserFriend (user1Id, user2Id) VALUES
(1, 2),
(1, 3);

-- Insertar datos en la tabla Messages
INSERT INTO Messages (msg, date, toUserId, fromUserId) VALUES
('Hello Alice!', '2023-10-01 10:00:00', 2, 1),
('Hi John!', '2023-10-01 10:05:00', 1, 2);

-- Insertar datos en la tabla Exercise
INSERT INTO Exercise (name, description, muscleTarget, muscleGroup, userId) VALUES
('Push Up', 'A basic push up exercise', 'Chest', 'Upper Body', 1),
('Squat', 'A basic squat exercise', 'Legs', 'Lower Body', 2);

-- Insertar datos en la tabla Routine
INSERT INTO Routine (name, description, type, startDate, endDate, visibility, userId) VALUES
    ('Morning Routine', 'A routine for morning exercises', 'Strength', '2023-10-01 06:00:00', '2023-10-01 07:00:00', TRUE, 1);

-- Insertar datos en la tabla DailyRoutine
INSERT INTO DailyRoutine (name, description, day, routineId) VALUES
    ('Monday Routine', 'Exercises for Monday', 1, 1);

-- Insertar datos en la tabla Workout
INSERT INTO Workout (targetSets, targetReps, targetKg, exerciseId, dailyRoutineId) VALUES
    (3, 10, 50.0, 1, 1);

-- Insertar datos en la tabla Sets
INSERT INTO Sets (numberSet, reps, kg, workoutId) VALUES
    (1, 10, 50.0, 1);


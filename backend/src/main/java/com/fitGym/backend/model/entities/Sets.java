 package com.fitGym.backend.model.entities;

import jakarta.persistence.*;

@Entity
public class Sets {

	private Long id;
	private int numberSet;
	private int reps;
	private float kg;
	private Workout workout;

	public Sets() {
	}

	public Sets(int numberSet, int reps, float kg, Workout workout) {
		this.numberSet = numberSet;
		this.reps = reps;
		this.kg = kg;
		this.workout = workout;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumberSet() {
		return numberSet;
	}

	public void setNumberSet(int numberSet) {
		this.numberSet = numberSet;
	}
	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public float getKg() {
		return kg;
	}

	public void setKg(float kg) {
		this.kg = kg;
	}

	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "workoutId")

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}



}
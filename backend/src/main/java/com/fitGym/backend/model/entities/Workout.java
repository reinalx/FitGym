
package com.fitGym.backend.model.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Workout {

	private Long id;
	private Integer targetSets;
	private Integer targetReps;
	private Float targetKg;
	private Exercise exercise;
	private DailyRoutine dailyRoutine;
	private Set<Sets> sets = new HashSet<>();

	public Workout() {
	}

	public Workout(Long id, Integer targetSets, Integer targetReps, Float targetKg, Exercise exercise,
			DailyRoutine dailyRoutine) {
		super();
		this.id = id;
		this.targetSets = targetSets;
		this.targetReps = targetReps;
		this.targetKg = targetKg;
		this.exercise = exercise;
		this.dailyRoutine = dailyRoutine;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTargetSets() {
		return targetSets;
	}
	public void setTargetSets(Integer targetSets) {
		this.targetSets = targetSets;
	}

	public Integer getTargetReps() {
		return targetReps;
	}

	public void setTargetReps(Integer targetReps) {
		this.targetReps = targetReps;
	}

	public Float getTargetKg() {
		return targetKg;
	}

	public void setTargetKg(Float targetKg) {
		this.targetKg = targetKg;
	}

	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "exerciseId")
	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "dailyRoutineId")
	public DailyRoutine getDailyRoutine() {
		return dailyRoutine;
	}

	public void setDailyRoutine(DailyRoutine dailyRoutine) {
		this.dailyRoutine = dailyRoutine;
	}
	@OneToMany(mappedBy = "workout")
	public Set<Sets> getSets() {
		return sets;
	}

	public void setSets(Set<Sets> sets) {
		this.sets = sets;
	}
}
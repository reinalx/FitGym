
package com.fitGym.backend.model.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
public class Workout {

	private Long id;
	private int targetSets;
	private int targetReps;
	private float targetKg;
	private Exercise exercise;
	private DailyRoutine dailyRoutine;
	private Set<Sets> sets = new HashSet<>();

	public Workout() {
	}

	public Workout( int targetSets, int targetReps, float targetKg, Exercise exercise,
			DailyRoutine dailyRoutine) {
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

	public int getTargetSets() {
		return targetSets;
	}
	public void setTargetSets(int targetSets) {
		this.targetSets = targetSets;
	}

	public int getTargetReps() {
		return targetReps;
	}

	public void setTargetReps(int targetReps) {
		this.targetReps = targetReps;
	}

	public float getTargetKg() {
		return targetKg;
	}

	public void setTargetKg(float targetKg) {
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

	public void addSet(Sets set) {
		sets.add(set);
		set.setWorkout(this);  // Sincroniza la relación bidireccionalmente
	}

	public void removeSet(Sets set) {
		sets.remove(set);
		set.setWorkout(null);  // Rompe la sincronización bidireccionalmente
	}

	@Transient
	public Optional<Sets> getSetsByNumber(int numberSet) {
		return sets.stream().filter(sets -> sets.getNumberSet() == numberSet).findFirst();
	}
	@Transient
	public int getNumberOfSets(){
		return sets.size();
	}


}
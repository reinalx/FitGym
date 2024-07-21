 package main.model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sets")

public class Sets {

	private Long id;
	private Integer reps;
	private Float kg;
	private Workout workout;

	public Sets() {
	}

	public Sets(Long id, Integer reps, Float kg, Workout workout) {
		super();
		this.id = id;
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

	public Integer getReps() {
		return reps;
	}

	public void setReps(Integer reps) {
		this.reps = reps;
	}

	public Float getKg() {
		return kg;
	}

	public void setKg(Float kg) {
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
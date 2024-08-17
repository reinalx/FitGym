
package com.fitGym.backend.model.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {


	private Long id;
	private String name;
	private String description;
    private String muscleTarget;
    private String muscleGroup;
	private User user;


    public Exercise() {
    }

	public Exercise(Long id, String name, String description, String muscleTarget, String muscleGroup, User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
        this.muscleTarget = muscleTarget;
        this.muscleGroup = muscleGroup;
		this.user = user;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public String getMuscleTarget() {
        return muscleTarget;
    }

    public void setMuscleTarget(String muscleTarget) {
        this.muscleTarget = muscleTarget;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
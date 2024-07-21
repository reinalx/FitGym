package main.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Routine {
    private Long id;
    private String name;
    private String description;
    private String type;
    private Date startDate;
    private Date endDate;
    private Set<DailyRoutine> dailyRoutines = new HashSet<>();
    private Set<RoutineUser> routineUsers = new HashSet<>();

    @id
    @GeneratedValue(stategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {}
    public void setName(String name) {}
    public String getDescription() {}
    public void setDescription(String description) {}
    public String getType() {}
    public void setType(String type) {}
    public Date getStartDate() {}
    public void setStartDate(Date startDate) {}
    public Date getEndDate() {}
    public void setEndDate(Date endDate) {}
    @OneToMany(mappedBy = "dailyRoutine")
    public Set<DailyRoutine> getDailyRoutines() {
        return dailyRoutines;
    }
    public void setDailyRoutines(Set<DailyRoutine> dailyRoutines) {
        this.dailyRoutines = dailyRoutines;
    }
    @OneToMany(mappedBy = "routineUser")
    public Set<RoutineUser> getRoutineUsers() {
        return routineUsers;
    }
    public void setRoutineUsers(Set<RoutineUser> routineUsers) {
        this.routineUsers = routineUsers;
    }


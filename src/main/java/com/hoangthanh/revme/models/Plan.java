package com.hoangthanh.revme.models;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_day")
    private String nameDay;
    
    @Column(name = "specific_date")
    private LocalDate specificDate;

    @Column(name = "description")
    private String description;

    @Column(name = "calories_burned_per_day")
    private Double caloriesBurnedPerDay;

    @Column(name = "calories_intake_per_day")
    private Double caloriesIntakePerDay;

    @Column(name = "water_intake_target")
    private Double waterIntakeTarget;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "plan")
	private List<WorkoutPlan> workoutPlans;
    
    @OneToMany(mappedBy = "plan")
	private List<MealPlan> mealPlans;
    
    @OneToMany(mappedBy = "plan")
	private List<Progress> progresses;
    
    public Plan() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameDay() {
		return nameDay;
	}

	public void setNameDay(String nameDay) {
		this.nameDay = nameDay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getCaloriesBurnedPerDay() {
		return caloriesBurnedPerDay;
	}

	public void setCaloriesBurnedPerDay(Double caloriesBurnedPerDay) {
		this.caloriesBurnedPerDay = caloriesBurnedPerDay;
	}

	public Double getCaloriesIntakePerDay() {
		return caloriesIntakePerDay;
	}

	public void setCaloriesIntakePerDay(Double caloriesIntakePerDay) {
		this.caloriesIntakePerDay = caloriesIntakePerDay;
	}

	public Double getWaterIntakeTarget() {
		return waterIntakeTarget;
	}

	public void setWaterIntakeTarget(Double waterIntakeTarget) {
		this.waterIntakeTarget = waterIntakeTarget;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getSpecificDate() {
		return specificDate;
	}

	public void setSpecificDate(LocalDate specificDate) {
		this.specificDate = specificDate;
	}
}

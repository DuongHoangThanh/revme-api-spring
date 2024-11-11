package com.hoangthanh.revme.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "progress")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "completed_workouts")
    private Integer completedWorkouts;

    @Column(name = "completed_meals")
    private Integer completedMeals;

    @Column(name = "total_calories_burned")
    private Double totalCaloriesBurned;

    @Column(name = "total_calories_intake")
    private Double totalCaloriesIntake;

    @Column(name = "total_water_intake")
    private Double totalWaterIntake;
    
    @Column(name = "workout_progress")
    private Double workoutProgress;
    
    @Column(name = "meal_progress")
    private Double mealProgress;

    @Column(name = "calories_burned_progress")
    private Double caloriesBurnedProgress;

    @Column(name = "calories_intake_progress")
    private Double caloriesIntakeProgress;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Progress() {}
    
    public Progress(Plan plan, int completedWorkouts) {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCompletedWorkouts() {
		return completedWorkouts;
	}

	public void setCompletedWorkouts(Integer completedWorkouts) {
		this.completedWorkouts = completedWorkouts;
	}

	public Integer getCompletedMeals() {
		return completedMeals;
	}

	public void setCompletedMeals(Integer completedMeals) {
		this.completedMeals = completedMeals;
	}

	public Double getTotalCaloriesBurned() {
		return totalCaloriesBurned;
	}

	public void setTotalCaloriesBurned(Double totalCaloriesBurned) {
		this.totalCaloriesBurned = totalCaloriesBurned;
	}

	public Double getTotalCaloriesIntake() {
		return totalCaloriesIntake;
	}

	public void setTotalCaloriesIntake(Double totalCaloriesIntake) {
		this.totalCaloriesIntake = totalCaloriesIntake;
	}

	public Double getTotalWaterIntake() {
		return totalWaterIntake;
	}

	public void setTotalWaterIntake(Double totalWaterIntake) {
		this.totalWaterIntake = totalWaterIntake;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
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

	public Double getWorkoutProgress() {
		return workoutProgress;
	}

	public void setWorkoutProgress(Double workoutProgress) {
		this.workoutProgress = workoutProgress;
	}

	public Double getMealProgress() {
		return mealProgress;
	}

	public void setMealProgress(Double mealProgress) {
		this.mealProgress = mealProgress;
	}

	public Double getCaloriesBurnedProgress() {
		return caloriesBurnedProgress;
	}

	public void setCaloriesBurnedProgress(Double caloriesBurnedProgress) {
		this.caloriesBurnedProgress = caloriesBurnedProgress;
	}

	public Double getCaloriesIntakeProgress() {
		return caloriesIntakeProgress;
	}

	public void setCaloriesIntakeProgress(Double caloriesIntakeProgress) {
		this.caloriesIntakeProgress = caloriesIntakeProgress;
	}
}

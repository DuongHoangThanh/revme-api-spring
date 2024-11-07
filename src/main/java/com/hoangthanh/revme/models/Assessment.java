package com.hoangthanh.revme.models;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "assessments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Assessment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Enumerated(EnumType.STRING)
	@Column(length = 6)
	private Gender gender;

	@NotNull
	private int age;

	private Double height;

	private Double weight;

	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	private ActivityLevel activityLevel;

	@Column(columnDefinition = "TEXT")
	private String healthConditions;

	@Column(columnDefinition = "TEXT")
	private String currentDiet;

	@Column(columnDefinition = "TEXT")
	private String dietaryPreferences;

	@Column(columnDefinition = "TEXT")
	private String favoriteFoods;

	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private WorkoutExperience workoutExperience;

	@Column(columnDefinition = "TEXT")
	private String preferredExercise;

	private Integer dailyWorkoutTime;

	@Enumerated(EnumType.STRING)
	@Column(length = 7)
	private SleepQuality sleepQuality;

	@Enumerated(EnumType.STRING)
	@Column(length = 8)
	private StressLevel stressLevel;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false, nullable = false)
	private Timestamp createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	public Assessment() {
	}

	public Assessment(User user, Gender gender, @NotNull int age, Double height, Double weight,
			ActivityLevel activityLevel, String healthConditions, String currentDiet, String dietaryPreferences,
			String favoriteFoods, WorkoutExperience workoutExperience, String preferredExercise,
			Integer dailyWorkoutTime, SleepQuality sleepQuality, StressLevel stressLevel) {
		super();
		this.user = user;
		this.gender = gender;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.activityLevel = activityLevel;
		this.healthConditions = healthConditions;
		this.currentDiet = currentDiet;
		this.dietaryPreferences = dietaryPreferences;
		this.favoriteFoods = favoriteFoods;
		this.workoutExperience = workoutExperience;
		this.preferredExercise = preferredExercise;
		this.dailyWorkoutTime = dailyWorkoutTime;
		this.sleepQuality = sleepQuality;
		this.stressLevel = stressLevel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public ActivityLevel getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(ActivityLevel activityLevel) {
		this.activityLevel = activityLevel;
	}

	public String getHealthConditions() {
		return healthConditions;
	}

	public void setHealthConditions(String healthConditions) {
		this.healthConditions = healthConditions;
	}

	public String getCurrentDiet() {
		return currentDiet;
	}

	public void setCurrentDiet(String currentDiet) {
		this.currentDiet = currentDiet;
	}

	public String getDietaryPreferences() {
		return dietaryPreferences;
	}

	public void setDietaryPreferences(String dietaryPreferences) {
		this.dietaryPreferences = dietaryPreferences;
	}

	public String getFavoriteFoods() {
		return favoriteFoods;
	}

	public void setFavoriteFoods(String favoriteFoods) {
		this.favoriteFoods = favoriteFoods;
	}

	public WorkoutExperience getWorkoutExperience() {
		return workoutExperience;
	}

	public void setWorkoutExperience(WorkoutExperience workoutExperience) {
		this.workoutExperience = workoutExperience;
	}

	public String getPreferredExercise() {
		return preferredExercise;
	}

	public void setPreferredExercise(String preferredExercise) {
		this.preferredExercise = preferredExercise;
	}

	public Integer getDailyWorkoutTime() {
		return dailyWorkoutTime;
	}

	public void setDailyWorkoutTime(Integer dailyWorkoutTime) {
		this.dailyWorkoutTime = dailyWorkoutTime;
	}

	public SleepQuality getSleepQuality() {
		return sleepQuality;
	}

	public void setSleepQuality(SleepQuality sleepQuality) {
		this.sleepQuality = sleepQuality;
	}

	public StressLevel getStressLevel() {
		return stressLevel;
	}

	public void setStressLevel(StressLevel stressLevel) {
		this.stressLevel = stressLevel;
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

	@Override
	public String toString() {
		return "gender: " + this.gender + "; age: " + this.age + "; height: " + this.height + "; weight: " + this.weight
				+ "; activity_level: " + this.activityLevel + "; health_conditions: " + this.healthConditions
				+ "; current_diet: " + this.currentDiet + ";\n dietary_preferences: " + this.dietaryPreferences
				+ "; favorite_foods: " + this.favoriteFoods + "; workout_experience: " + this.workoutExperience
				+ "; preferred_exercise: " + this.preferredExercise + "; daily_workout_time: " + this.dailyWorkoutTime
				+ "; sleep_quality: " + this.sleepQuality + "; stress_level: " + this.stressLevel;
	}

}

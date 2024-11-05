package com.hoangthanh.revme.request;


import com.hoangthanh.revme.models.ActivityLevel;
import com.hoangthanh.revme.models.Gender;
import com.hoangthanh.revme.models.SleepQuality;
import com.hoangthanh.revme.models.StressLevel;
import com.hoangthanh.revme.models.WorkoutExperience;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentRequest {
	
	private Long id;
    private Long userId;
    private Gender gender;
    private int age;
    private Double height;
    private Double weight;
    private ActivityLevel activityLevel;
    private String healthConditions;
    private String currentDiet;
    private String dietaryPreferences;
    private String favoriteFoods;
    private WorkoutExperience workoutExperience;
    private String preferredExercise;
    private Integer dailyWorkoutTime;
    private SleepQuality sleepQuality;
    private StressLevel stressLevel;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
    
}

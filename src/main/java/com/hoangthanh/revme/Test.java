package com.hoangthanh.revme;

import com.hoangthanh.revme.models.ActivityLevel;
import com.hoangthanh.revme.models.Assessment;
import com.hoangthanh.revme.models.Gender;
import com.hoangthanh.revme.models.SleepQuality;
import com.hoangthanh.revme.models.StressLevel;
import com.hoangthanh.revme.models.WorkoutExperience;

public class Test {
	public static void main(String[] args) {
		
		Assessment assessment = new Assessment();
		assessment.setGender(Gender.Female);
		assessment.setAge(21);
		assessment.setHeight(1.62);
		assessment.setWeight(56.5);
		assessment.setActivityLevel(ActivityLevel.ModeratelyActive);
		assessment.setHealthConditions("Good");
		assessment.setCurrentDiet("Good");
		assessment.setDietaryPreferences("dietaryPreferences");
		assessment.setFavoriteFoods("Egg");
		assessment.setWorkoutExperience(WorkoutExperience.Beginner);
		assessment.setPreferredExercise("preferredExercise");
		assessment.setDailyWorkoutTime(2);
		assessment.setSleepQuality(SleepQuality.Good);
		assessment.setStressLevel(StressLevel.Low);
		
		System.out.println(assessment.toString());
	}

}

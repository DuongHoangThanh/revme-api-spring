package com.hoangthanh.revme.response;

import java.util.List;

import com.hoangthanh.revme.models.MealPlan;
import com.hoangthanh.revme.models.Progress;

public class MealResponse {
	private List<MealPlan> mealPlans;
    private Progress progress;

    public MealResponse(List<MealPlan> mealPlans, Progress progress) {
        this.mealPlans = mealPlans;
        this.progress = progress;
    }

	public List<MealPlan> getMealPlans() {
		return mealPlans;
	}

	public void setMealPlans(List<MealPlan> mealPlans) {
		this.mealPlans = mealPlans;
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}
}

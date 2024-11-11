package com.hoangthanh.revme.response;

import java.util.List;

import com.hoangthanh.revme.models.Progress;
import com.hoangthanh.revme.models.WorkoutPlan;

public class WorkoutResponse {
	
	private List<WorkoutPlan> workoutPlans;
    private Progress progress;

    public WorkoutResponse(List<WorkoutPlan> workoutPlans, Progress progress) {
        this.workoutPlans = workoutPlans;
        this.progress = progress;
    }

    public List<WorkoutPlan> getWorkoutPlans() {
        return workoutPlans;
    }

    public void setWorkoutPlans(List<WorkoutPlan> workoutPlans) {
        this.workoutPlans = workoutPlans;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}

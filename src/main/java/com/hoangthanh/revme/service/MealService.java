package com.hoangthanh.revme.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangthanh.revme.models.MealPlan;
import com.hoangthanh.revme.models.Plan;
import com.hoangthanh.revme.models.Progress;
import com.hoangthanh.revme.models.WorkoutPlan;
import com.hoangthanh.revme.repository.MealPlanRepository;
import com.hoangthanh.revme.repository.PlanRepository;
import com.hoangthanh.revme.repository.ProgressRepository;
import com.hoangthanh.revme.repository.UserRepository;
import com.hoangthanh.revme.repository.WorkoutPlanRepository;
import com.hoangthanh.revme.response.MealResponse;
import com.hoangthanh.revme.response.WorkoutResponse;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MealService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private MealPlanRepository mealPlanRepository;
    
    @Autowired
    private ProgressRepository progressRepository;

    // Lấy danh sách MealPlan và MealProgress theo ngày
    public MealResponse getMealPlansAndProgressByDate(LocalDate date, Long userId) {
        Plan plan = planRepository.findBySpecificDateAndUser_Id(date, userId)
                .orElseThrow(() -> new EntityNotFoundException("No plan found for the specified date"));

        List<MealPlan> mealPlans = mealPlanRepository.findByPlan(plan);

        Progress progress = progressRepository.findByPlan(plan)
                .orElseThrow(() -> new EntityNotFoundException("Progress not found for the plan"));

        return new MealResponse(mealPlans, progress);
    }

    // Lấy chi tiết của một MealPlan
    public MealPlan getMealPlanDetails(Long mealPlanId) {
        return mealPlanRepository.findById(mealPlanId)
                .orElseThrow(() -> new EntityNotFoundException("MealPlan not found"));
    }

    // Cập nhật trạng thái hoàn thành bữa ăn và tiến độ
    public MealResponse completeMeal(Long mealPlanId) {
        MealPlan mealPlan = mealPlanRepository.findById(mealPlanId)
                .orElseThrow(() -> new EntityNotFoundException("MealPlan not found with ID: " + mealPlanId));

        // Kiểm tra nếu bữa ăn đã hoàn thành thì không cần cập nhật lại
        if (mealPlan.getStatus()) {
            throw new RuntimeException("MealPlan already completed");
        }

        // Đánh dấu bữa ăn là hoàn thành
        mealPlan.setStatus(true);
        mealPlanRepository.save(mealPlan);

        // Cập nhật tiến độ
        Plan plan = mealPlan.getPlan();
        
        Progress progress = null;
        try {
        	progress = progressRepository.findByPlan(plan)
                    .orElseThrow(() -> new EntityNotFoundException("Progress not found for the plan"));
		} catch (Exception e) {
			System.out.println(e);
		}

        // Tăng số lượng bữa ăn đã hoàn thành
        progress.setCompletedMeals(progress.getCompletedMeals() + 1);

        // Tính toán phần trăm hoàn thành
        int totalMeals = plan.getTotalMealsTarget();
        int completedMeals = progress.getCompletedMeals();
        double mealProgressPercentage = (totalMeals > 0) ? ((double) completedMeals / totalMeals) * 100 : 0;
        progress.setMealProgress(mealProgressPercentage);

        progressRepository.save(progress);

        // Lấy lại danh sách bữa ăn và tiến độ để trả về
        List<MealPlan> mealPlans = mealPlanRepository.findByPlan(plan);

        return new MealResponse(mealPlans, progress);
    }
}

package com.hoangthanh.revme.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangthanh.revme.models.Plan;
import com.hoangthanh.revme.models.Progress;
import com.hoangthanh.revme.models.WorkoutPlan;
import com.hoangthanh.revme.repository.PlanRepository;
import com.hoangthanh.revme.repository.ProgressRepository;
import com.hoangthanh.revme.repository.UserRepository;
import com.hoangthanh.revme.repository.WorkoutPlanRepository;
import com.hoangthanh.revme.response.WorkoutResponse;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WorkoutService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private UserRepository userRepository;

    // Lấy danh sách WorkoutPlan và Progress theo ngày
    public WorkoutResponse getWorkoutPlansAndProgressByDate(LocalDate date, Long userId) {
        Plan plan = planRepository.findBySpecificDateAndUser_Id(date, userId)
                .orElseThrow(() -> new EntityNotFoundException("No plan found for the specified date"));

        List<WorkoutPlan> workoutPlans = workoutPlanRepository.findByPlan(plan);

        Progress progress = progressRepository.findByPlan(plan)
                .orElseThrow(() -> new EntityNotFoundException("Progress not found for the plan"));

        return new WorkoutResponse(workoutPlans, progress);
    }

    // Lấy chi tiết của một WorkoutPlan
    public WorkoutPlan getWorkoutPlanDetails(Long workoutId) {
        return workoutPlanRepository.findById(workoutId)
                .orElseThrow(() -> new EntityNotFoundException("WorkoutPlan not found"));
    }

    // Cập nhật trạng thái hoàn thành bài tập và tiến độ
    public WorkoutResponse completeWorkout(Long workoutPlanId) {
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutPlanId)
                .orElseThrow(() -> new EntityNotFoundException("WorkoutPlan not found with ID: " + workoutPlanId));

        // Kiểm tra nếu bài tập đã hoàn thành thì không cần cập nhật lại
        if (workoutPlan.getStatus()) {
            throw new RuntimeException("WorkoutPlan already completed");
        }

        // Đánh dấu bài tập là hoàn thành
        workoutPlan.setStatus(true);
        workoutPlanRepository.save(workoutPlan);

        // Cập nhật tiến độ
        Plan plan = workoutPlan.getPlan();
        Progress progress = progressRepository.findByPlan(plan)
                .orElseThrow(() -> new EntityNotFoundException("Progress not found for the plan"));

        // Tăng số lượng bài tập đã hoàn thành
        progress.setCompletedWorkouts(progress.getCompletedWorkouts() + 1);

        // Tính toán phần trăm hoàn thành
        int totalWorkouts = plan.getTotalWorkoutsTarget();
        int completedWorkouts = progress.getCompletedWorkouts();
        double workoutProgress = (totalWorkouts > 0) ? ((double) completedWorkouts / totalWorkouts) * 100 : 0;
        progress.setWorkoutProgress(workoutProgress);

        progressRepository.save(progress);

        // Lấy lại danh sách bài tập và tiến độ để trả về
        List<WorkoutPlan> workoutPlans = workoutPlanRepository.findByPlan(plan);

        return new WorkoutResponse(workoutPlans, progress);
    }
}


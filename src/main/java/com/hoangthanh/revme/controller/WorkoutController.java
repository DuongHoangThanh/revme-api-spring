package com.hoangthanh.revme.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoangthanh.revme.models.WorkoutPlan;
import com.hoangthanh.revme.repository.UserRepository;
import com.hoangthanh.revme.response.WorkoutResponse;
import com.hoangthanh.revme.security.services.UserDetailsImpl;
import com.hoangthanh.revme.service.WorkoutService;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{date}")
    public ResponseEntity<?> getWorkoutPlansByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();

        WorkoutResponse workoutResponse = workoutService.getWorkoutPlansAndProgressByDate(date, userId);
        return ResponseEntity.ok(workoutResponse);
    }

    // API lấy chi tiết của một WorkoutPlan
    @GetMapping("/details/{workoutId}")
    public ResponseEntity<?> getWorkoutPlanDetails(@PathVariable Long workoutId) {
        WorkoutPlan workoutPlan = workoutService.getWorkoutPlanDetails(workoutId);
        return ResponseEntity.ok(workoutPlan);
    }

    // API cập nhật trạng thái hoàn thành bài tập
    @PostMapping("/complete/{workoutId}")
    public ResponseEntity<?> completeWorkout(@PathVariable Long workoutId) {
        try {
            WorkoutResponse workoutResponse = workoutService.completeWorkout(workoutId);
            return ResponseEntity.ok(workoutResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
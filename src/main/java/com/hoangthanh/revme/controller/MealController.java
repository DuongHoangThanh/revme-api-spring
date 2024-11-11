package com.hoangthanh.revme.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoangthanh.revme.models.MealPlan;
import com.hoangthanh.revme.models.WorkoutPlan;
import com.hoangthanh.revme.response.MealResponse;
import com.hoangthanh.revme.response.WorkoutResponse;
import com.hoangthanh.revme.security.services.UserDetailsImpl;
import com.hoangthanh.revme.service.MealService;
import com.hoangthanh.revme.service.WorkoutService;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping("/{date}")
    public ResponseEntity<?> getMealPlansByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();

        MealResponse mealResponse = mealService.getMealPlansAndProgressByDate(date, userId);
        return ResponseEntity.ok(mealResponse);
    }

    @GetMapping("/details/{mealId}")
    public ResponseEntity<?> getMealPlanDetails(@PathVariable Long mealId) {
        MealPlan mealPlan = mealService.getMealPlanDetails(mealId);
        return ResponseEntity.ok(mealPlan);
    }

    @PostMapping("/complete/{mealId}")
    public ResponseEntity<?> completeMealP(@PathVariable Long mealId) {
        try {
            MealResponse mealResponse = mealService.completeMeal(mealId);
            return ResponseEntity.ok(mealResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

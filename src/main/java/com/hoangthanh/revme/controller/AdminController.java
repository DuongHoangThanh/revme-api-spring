package com.hoangthanh.revme.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoangthanh.revme.models.Exercise;
import com.hoangthanh.revme.models.User;
import com.hoangthanh.revme.repository.FoodRepository;
import com.hoangthanh.revme.service.ExerciseService;
import com.hoangthanh.revme.service.FoodService;
import com.hoangthanh.revme.service.PlanService;
import com.hoangthanh.revme.service.UserService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExerciseService exerciseService;
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private PlanService planService;
	
	@GetMapping("/accounts/list")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
	
	@GetMapping("/accounts/total")
    public Map<String, Long> getTotalAccounts() {
        long totalAccounts = userService.getTotalUsers();
        return Map.of("total", totalAccounts);
    }

    @GetMapping("/exercises/total")
    public Map<String, Long> getTotalExercises() {
        long totalExercises = exerciseService.getTotalExercise();
        return Map.of("total", totalExercises);
    }

    @GetMapping("/foods/total")
    public Map<String, Long> getTotalMenus() {
        long totalMenus = foodService.getTotalFood();
        return Map.of("total", totalMenus);
    }

    @GetMapping("/plans/total")
    public Map<String, Long> getTotalPlans() {
        long totalPlans = planService.getTotalPlan();
        return Map.of("total", totalPlans);
    }

    @GetMapping("/visits")
    public Map<String, Object> getVisitsData() {
        return Map.of(
            "dates", List.of("2024-11-01", "2024-11-02", "2024-11-03", "2024-11-04"),
            "visits", List.of(100, 120, 90, 110)
        );
    }
    
}


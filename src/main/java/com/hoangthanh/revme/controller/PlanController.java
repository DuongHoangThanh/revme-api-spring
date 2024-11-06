package com.hoangthanh.revme.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoangthanh.revme.models.Assessment;
import com.hoangthanh.revme.models.Exercise;
import com.hoangthanh.revme.models.Food;
import com.hoangthanh.revme.models.User;
import com.hoangthanh.revme.repository.ExerciseRepository;
import com.hoangthanh.revme.repository.FoodRepository;
import com.hoangthanh.revme.repository.UserRepository;
import com.hoangthanh.revme.security.services.UserDetailsImpl;
import com.hoangthanh.revme.service.PlanService;

@RestController
@RequestMapping("/api/plan")
public class PlanController {

	@Autowired
	private PlanService planService;

	@Autowired
	private ExerciseRepository exerciseRepository;

	@Autowired
	private FoodRepository foodRepository;
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/generate-and-save")
	public ResponseEntity<String> generateAndSavePlan(@RequestParam Assessment assessment) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		Long userId = userDetails.getId();
		
		Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("Không tìm thấy người dùng với ID: " + userId);
        }
        User user = userOptional.get();
		

		List<Exercise> exercises = exerciseRepository.findAll();
		List<Food> foods = foodRepository.findAll();

		try {
			JSONObject generatedData = planService.generatePlan(assessment, exercises, foods);

			planService.saveGeneratedPlan(generatedData, user);

			return ResponseEntity.ok("Plan generated and saved successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
	}
}

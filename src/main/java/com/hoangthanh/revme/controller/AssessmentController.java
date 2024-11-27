package com.hoangthanh.revme.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoangthanh.revme.models.Assessment;
import com.hoangthanh.revme.models.User;
import com.hoangthanh.revme.repository.UserRepository;
import com.hoangthanh.revme.request.AssessmentRequest;
import com.hoangthanh.revme.response.MessageResponse;
import com.hoangthanh.revme.security.services.UserDetailsImpl;
import com.hoangthanh.revme.service.AssessmentService;

import jakarta.persistence.EntityNotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

	@Autowired
	private AssessmentService assessmentService;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<Assessment> getAllAssessment() {
		return assessmentService.getAllAssessments();
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<Assessment> getAssessmentById(@PathVariable Long id) {
//		return assessmentService.getAssessmentById(id).map(ResponseEntity::ok)
//				.orElse(ResponseEntity.notFound().build());
//	}
	
	@GetMapping("/{id}")
	public Assessment getAssessmentById(@PathVariable Long id) {
		return assessmentService.getAssessmentById(id).orElseThrow(() -> new EntityNotFoundException("Assessment not found with id: " + id));
	}
	
	@PostMapping
	public Assessment createAssessment(@RequestBody AssessmentRequest assessmentRequest) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		Long userId = userDetails.getId();
		
		Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("Không tìm thấy người dùng với ID: " + userId);
        }
        User user = userOptional.get();
		
		Assessment assessment = new Assessment();
        assessment.setUser(user);
        assessment.setGender(assessmentRequest.getGender());
        assessment.setAge(assessmentRequest.getAge());
        assessment.setHeight(assessmentRequest.getHeight());
        assessment.setWeight(assessmentRequest.getWeight());
        assessment.setActivityLevel(assessmentRequest.getActivityLevel());
        assessment.setHealthConditions(assessmentRequest.getHealthConditions());
        assessment.setCurrentDiet(assessmentRequest.getCurrentDiet());
        assessment.setDietaryPreferences(assessmentRequest.getDietaryPreferences());
        assessment.setFavoriteFoods(assessmentRequest.getFavoriteFoods());
        assessment.setWorkoutExperience(assessmentRequest.getWorkoutExperience());
        assessment.setPreferredExercise(assessmentRequest.getPreferredExercise());
        assessment.setDailyWorkoutTime(assessmentRequest.getDailyWorkoutTime());
        assessment.setSleepQuality(assessmentRequest.getSleepQuality());
        assessment.setStressLevel(assessmentRequest.getStressLevel());
        
//        assessmentService.saveAssessment(assessment);
//        return ResponseEntity.ok(new MessageResponse("Added successfully!"));
        
        return assessmentService.saveAssessment(assessment);
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<Assessment> updateAssessment(@PathVariable Long id,
//			@RequestBody Assessment assessmentDetails) {
//		return assessmentService.getAssessmentById(id).map(existingAssessment -> {
//			existingAssessment.setAge(assessmentDetails.getAge());
//			existingAssessment.setHeight(assessmentDetails.getHeight());
//			existingAssessment.setWeight(assessmentDetails.getWeight());
//			existingAssessment.setActivityLevel(assessmentDetails.getActivityLevel());
//			existingAssessment.setHealthConditions(assessmentDetails.getHealthConditions());
//			existingAssessment.setCurrentDiet(assessmentDetails.getCurrentDiet());
//			existingAssessment.setDietaryPreferences(assessmentDetails.getDietaryPreferences());
//			existingAssessment.setFavoriteFoods(assessmentDetails.getFavoriteFoods());
//			existingAssessment.setWorkoutExperience(assessmentDetails.getWorkoutExperience());
//			existingAssessment.setPreferredExercise(assessmentDetails.getPreferredExercise());
//			existingAssessment.setDailyWorkoutTime(assessmentDetails.getDailyWorkoutTime());
//			existingAssessment.setSleepQuality(assessmentDetails.getSleepQuality());
//			existingAssessment.setStressLevel(assessmentDetails.getStressLevel());
//
//			Assessment updatedAssessment = assessmentService.saveAssessment(existingAssessment);
//			return ResponseEntity.ok(updatedAssessment);
//		}).orElse(ResponseEntity.notFound().build());
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deleteAssessment(@PathVariable Long id) {
//		return assessmentService.getAssessmentById(id).map(assessment -> {
//			assessmentService.deleteAssessment(id);
//			return ResponseEntity.noContent().build();
//		}).orElse(ResponseEntity.notFound().build());
//	}

}

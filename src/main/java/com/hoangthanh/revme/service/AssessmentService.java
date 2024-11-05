package com.hoangthanh.revme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangthanh.revme.models.Assessment;
import com.hoangthanh.revme.repository.AssessmentRepository;

@Service
public class AssessmentService {
	
	@Autowired
	private AssessmentRepository assessmentRepository;
	
	public List<Assessment> getAllAssessments() {
		return assessmentRepository.findAll();
	}
	
	public Optional<Assessment> getAssessmentById(Long id) {
		return assessmentRepository.findById(id);
	}
	
	public Assessment saveAssessment(Assessment assessment) {
		return assessmentRepository.save(assessment);
	}
	
	public void deleteAssessment(Long id) {
		assessmentRepository.deleteById(id);
	}
}

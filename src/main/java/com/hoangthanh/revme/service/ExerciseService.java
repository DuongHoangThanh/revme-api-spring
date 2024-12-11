package com.hoangthanh.revme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangthanh.revme.repository.ExerciseRepository;

@Service
public class ExerciseService {
	
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	public long getTotalExercise() {
		return exerciseRepository.count();
	}
}

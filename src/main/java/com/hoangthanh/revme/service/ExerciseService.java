package com.hoangthanh.revme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangthanh.revme.models.Exercise;
import com.hoangthanh.revme.repository.ExerciseRepository;

@Service
public class ExerciseService {
	
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	public long getTotalExercise() {
		return exerciseRepository.count();
	}
	
	// Lấy danh sách tất cả các bài tập
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    // Tìm bài tập theo ID
    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findById(id);
    }

    // Thêm bài tập mới
    public Exercise addExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    // Sửa bài tập
    public Exercise updateExercise(Long id, Exercise exerciseDetails) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found"));
        exercise.setName(exerciseDetails.getName());
        exercise.setDescription(exerciseDetails.getDescription());
        exercise.setImageUrl(exerciseDetails.getImageUrl());
        exercise.setVideoUrl(exerciseDetails.getVideoUrl());
        exercise.setReps(exerciseDetails.getReps());
        exercise.setSets(exerciseDetails.getSets());
        exercise.setDurationMinutes(exerciseDetails.getDurationMinutes());
        exercise.setCalories(exerciseDetails.getCalories());
        exercise.setUpdatedAt(exerciseDetails.getUpdatedAt());
        return exerciseRepository.save(exercise);
    }

    // Xóa bài tập
    public void deleteExercise(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found"));
        exerciseRepository.delete(exercise);
    }
}

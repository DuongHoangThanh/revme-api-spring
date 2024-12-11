package com.hoangthanh.revme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangthanh.revme.models.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}

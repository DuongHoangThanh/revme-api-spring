package com.hoangthanh.revme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangthanh.revme.models.Assessment;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
	
}

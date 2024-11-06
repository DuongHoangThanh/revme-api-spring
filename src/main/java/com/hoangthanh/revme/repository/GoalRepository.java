package com.hoangthanh.revme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoangthanh.revme.models.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {
	
}

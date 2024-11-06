package com.hoangthanh.revme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangthanh.revme.models.MealPlan;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
	
}

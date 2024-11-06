package com.hoangthanh.revme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangthanh.revme.models.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
	
}

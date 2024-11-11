package com.hoangthanh.revme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangthanh.revme.models.Plan;
import com.hoangthanh.revme.models.WorkoutPlan;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long>  {
	List<WorkoutPlan> findByPlan(Plan plan);
}

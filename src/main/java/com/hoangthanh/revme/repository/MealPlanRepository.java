package com.hoangthanh.revme.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hoangthanh.revme.models.MealPlan;
import com.hoangthanh.revme.models.Plan;
import com.hoangthanh.revme.models.WorkoutPlan;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
//	@Query("SELECT mp FROM MealPlan mp WHERE mp.plan.specificDate = :specificDate AND mp.plan.user.id = :userId")
//    List<MealPlan> findBySpecificDateAndUserId(@Param("specificDate") LocalDate specificDate, @Param("userId") Long userId);
	
	List<MealPlan> findByPlan(Plan plan);
}

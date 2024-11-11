package com.hoangthanh.revme.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangthanh.revme.models.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
//	Optional<Plan> findBySpecificDateAndUserId(LocalDate specificDate, Long userId);
	Optional<Plan> findBySpecificDateAndUser_Id(LocalDate date, Long userId);
}

package com.hoangthanh.revme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangthanh.revme.models.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

}

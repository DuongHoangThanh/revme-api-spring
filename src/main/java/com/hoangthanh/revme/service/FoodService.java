package com.hoangthanh.revme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangthanh.revme.models.Food;
import com.hoangthanh.revme.repository.FoodRepository;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    // Lấy tổng số lượng Food
    public long getTotalFood() {
        return foodRepository.count();
    }

    // Lấy danh sách tất cả Food
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    // Tìm Food theo ID
    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    // Thêm Food mới
    public Food addFood(Food food) {
        return foodRepository.save(food);
    }

    // Sửa thông tin Food
    public Food updateFood(Long id, Food foodDetails) {
        Food food = foodRepository.findById(id).orElseThrow(() -> new RuntimeException("Food not found"));
        food.setName(foodDetails.getName());
        food.setDescription(foodDetails.getDescription());
        food.setImageUrl(foodDetails.getImageUrl());
        food.setCalories(foodDetails.getCalories());
        food.setProtein(foodDetails.getProtein());
        food.setCarbs(foodDetails.getCarbs());
        food.setFat(foodDetails.getFat());
        food.setUpdatedAt(foodDetails.getUpdatedAt());
        return foodRepository.save(food);
    }

    // Xóa Food
    public void deleteFood(Long id) {
        Food food = foodRepository.findById(id).orElseThrow(() -> new RuntimeException("Food not found"));
        foodRepository.delete(food);
    }
}

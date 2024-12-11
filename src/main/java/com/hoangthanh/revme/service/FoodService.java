package com.hoangthanh.revme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangthanh.revme.repository.FoodRepository;

@Service
public class FoodService {

	@Autowired
	private FoodRepository foodRepository;
	
	public long getTotalFood() {
		return foodRepository.count();
	}
}

package com.hoangthanh.revme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangthanh.revme.models.User;
import com.hoangthanh.revme.repository.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;

    public long getTotalUsers() {
        return userRepository.count();
    }
    
    public List<User> getAllUser() {
    	return userRepository.findAll();
    }
    
    
}

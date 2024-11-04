package com.hoangthanh.revme.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangthanh.revme.models.User;
import com.hoangthanh.revme.repository.UserRepository;



@Service
public class Userservice {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

}

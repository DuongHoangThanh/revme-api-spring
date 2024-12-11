package com.hoangthanh.revme.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.hoangthanh.revme.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class AdminViewController {
	@GetMapping("/api/admin/login")
	public String loginPage() {
        return "login";
    }
	
	@GetMapping("/api/admin/dashboard")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public String dashboard() {
        return "dashboard";
    }
	
	@GetMapping("/api/admin/account")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public String account() {
        return "account";
	}
    
}
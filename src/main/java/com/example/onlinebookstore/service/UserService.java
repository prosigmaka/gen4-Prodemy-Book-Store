package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.entity.User;
import com.example.onlinebookstore.model.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}

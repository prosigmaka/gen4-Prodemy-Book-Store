package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserSecurityService extends UserDetailsService {
    User save(User user);
}

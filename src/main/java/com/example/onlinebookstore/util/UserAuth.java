/**
 *
 */
package com.example.onlinebookstore.util;

import com.example.onlinebookstore.model.entity.User;
import com.example.onlinebookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuth {
    @Autowired
    private static UserRepository userRepository;

    public static User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findByUserName(userDetail.getUsername());

        return user;
    }
}

package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.dto.LoginDto;
import com.example.onlinebookstore.model.entity.Role;

import com.example.onlinebookstore.model.entity.User;
import com.example.onlinebookstore.repository.RoleRepository;
import com.example.onlinebookstore.repository.UserRepository;
import com.example.onlinebookstore.model.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    //constructor
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    //save registration data to database
    @Override
    public User save(UserRegistrationDto registrationDto) {
//        User user = new User(registrationDto.getFirstName(),
//                registrationDto.getLastName(), registrationDto.getUsername(), registrationDto.getEmail(), registrationDto.getAddress(), registrationDto.getPhone(),
//                passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));

//        Role role = roleRepository.findById(2L).get();
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setUsername(registrationDto.getUsername());
        user.setEmailUser(registrationDto.getEmailUser());
        user.setAddress(registrationDto.getAddress());
        user.setPhone(registrationDto.getPhone());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
//        user.setRole(role);
        user.setRoleId(2L);
        return userRepository.save(user);
    }

//    @Override
//    public void saveLoginDto(User user) {
//
//    }

//    @Override
//    public void saveLoginDto(User user) {
//
//    }

    //this is provided because UserService extends UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //if the user null, throw the message below
        //if not null, we just create a User object, that is provided by spring security
        //then we pass the email, password and roles to User object
        User user = userRepository.findByEmailUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password!");
        } else {
            LoginDto loginDto = new LoginDto();
            loginDto.setIdUserLogin(user.getId());
        }

//        LoginDto loginDto = new LoginDto();
        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), mapRolesToAuthorities(Arrays.asList(user.getRoles()))); //get the role from user object
    }

//    public void saveLoginDto (User user) {
//        LoginDto loginDto = new LoginDto();
//        loginDto.setIdUserLogin(user.getId());
//        return userRepository.saveLoginDto(user);
//    }

//    @Override
//    public User save (LoginDto loginDto) {
//        User user = new User();
//        user.setId(loginDto.getIdUserLogin());
//        return userRepository.save(user);
//    }

    //convert role into authorities, because spring security expecting authorities
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        //what we do above is convert roles into stream, then map role and convert it into SimpleGrantedAuthority object (spring security class)
        //then we pass Name to SimpleGrantedAuthority class, and we collected the stream into a List
        //we return it as a List
    }

}
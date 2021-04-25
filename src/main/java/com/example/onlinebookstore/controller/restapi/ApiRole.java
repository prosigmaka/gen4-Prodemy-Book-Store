package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.RoleRequestDto;
import com.example.onlinebookstore.model.dto.RoleResponseDto;
import com.example.onlinebookstore.model.entity.Role;
import com.example.onlinebookstore.model.entity.User;
import com.example.onlinebookstore.repository.RoleRepository;
import com.example.onlinebookstore.util.UserAuth;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/role")
public class ApiRole {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping()
    public List<RoleResponseDto> getList() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponseDto> roleResponseDtos =
                roles.stream()
                        .map(role -> modelMapper.map(role, RoleResponseDto.class))
                        .collect(Collectors.toList());
        return roleResponseDtos;
    }

    @PostMapping
    public RoleResponseDto save(@RequestBody RoleRequestDto roleRequestDto) {
        User userLogin = UserAuth.getUser();
        Role role = modelMapper.map(roleRequestDto, Role.class);

        role.setCreatedBy(userLogin.getId());
        role.setCreatedOn(new Date());
        Role roleResponse = roleRepository.save(role);
        RoleResponseDto roleResponseDto = modelMapper.map(roleResponse, RoleResponseDto.class);

        return roleResponseDto;
    }
}

package com.example.onlinebookstore.controller.mvc;

import com.example.onlinebookstore.service.UserService;
import com.example.onlinebookstore.model.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	private UserService userService;

	//constructor
//	public UserRegistrationController(UserService userService) {
//		this.userService = userService;
//	}

	//user object used in registration.html
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    //show registration.html
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	//"user" object contain form data
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success"; //success message
	}
}

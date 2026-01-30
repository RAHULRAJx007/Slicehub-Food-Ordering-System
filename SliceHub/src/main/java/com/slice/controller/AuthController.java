package com.slice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.slice.model.User;
import com.slice.service.UserService;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
    	
    	userService.registerCustomer(user);
    	
    	model.addAttribute("message", "Verification Link sent to your Email.");
    	return "login";
    }
    
    @GetMapping("Verify")
    public String verifyUser(@RequestParam("token") String token, Model model) {
    	
    	try {
    		userService.verifyUser(token);
    		model.addAttribute("message", "Account verified successfully. you can login now!");
    	} catch (Exception e) {
    		model.addAttribute("error", e.getMessage());
    	}
    	
    	return "login";
    }
}

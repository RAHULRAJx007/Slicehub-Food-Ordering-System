package com.slice.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.slice.model.User;
import com.slice.service.UserService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private UserService userService;

    @GetMapping("/account")
    public String showAccountPage(Model model, Principal principal) {

        // Safety check (VERY IMPORTANT)
        if (principal == null) {
            return "redirect:/login";
        }

        // Logged-in user's email
        String email = principal.getName();

        // Fetch user from DB
        User user = userService.findByEmail(email);

        // Extra safety
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        // OPTIONAL defaults (prevents Thymeleaf null errors)
        model.addAttribute("orders", null);
        model.addAttribute("totalOrders", 0);
        model.addAttribute("loyaltyPoints", 0);
        model.addAttribute("memberSince", "");

        return "customer/account";
    }
}

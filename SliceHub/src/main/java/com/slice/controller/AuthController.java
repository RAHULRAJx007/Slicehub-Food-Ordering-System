package com.slice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.slice.model.User;
import com.slice.service.PizzaService;
import com.slice.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PizzaService pizzaService;   // ✅ INJECT SERVICE

    // ✅ HOME PAGE – LOAD PIZZAS FROM DB
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pizzas", pizzaService.getAvailablePizzas());
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

    @GetMapping("/aboutpage")
    public String aboutpage() {
        return "about";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        userService.registerCustomer(user);
        model.addAttribute("message", "Verification Link sent to your Email.");
        return "login";
    }

    @GetMapping("/Verify")
    public String verifyUser(@RequestParam("token") String token, Model model) {
        try {
            userService.verifyUser(token);
            model.addAttribute("message", "Account verified successfully. You can login now!");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "login";
    }
}

package com.travel.unified.controller;

import com.travel.unified.model.User;
import com.travel.unified.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, org.springframework.ui.Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/auth/login?registerSuccess=true";
        } catch (Exception e) {
            return "redirect:/auth/register?error=true";
        }
    }
}

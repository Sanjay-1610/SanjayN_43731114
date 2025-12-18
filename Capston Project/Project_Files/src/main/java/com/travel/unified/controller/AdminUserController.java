package com.travel.unified.controller;

import com.travel.unified.model.User;
import com.travel.unified.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showManageUsersPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/manage-users";
    }
}

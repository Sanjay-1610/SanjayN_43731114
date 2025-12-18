package com.travel.unified.controller;

import com.travel.unified.model.Food;
import com.travel.unified.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/foods")
    public String showManageFoodPage(Model model) {
        List<Food> foods = foodService.getAllFood();
        model.addAttribute("foods", foods);
        return "admin/manage-food";
    }

    @PostMapping("/food/save")
    public String saveFood(@ModelAttribute Food food) {
        foodService.saveFood(food);
        return "redirect:/admin/foods";
    }

    @GetMapping("/food/delete/{id}")
    public String deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return "redirect:/admin/foods";
    }
}

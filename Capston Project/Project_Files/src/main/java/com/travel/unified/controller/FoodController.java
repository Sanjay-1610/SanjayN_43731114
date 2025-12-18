package com.travel.unified.controller;

import com.travel.unified.model.Food;
import com.travel.unified.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping
    public String showFoodList(@RequestParam(required = false) String city,
            @RequestParam(required = false) String type,
            Model model) {
        List<Food> foodList = foodService.searchFood(city, type);
        model.addAttribute("foodList", foodList);
        return "food/food-list";
    }

    @GetMapping("/order")
    public String orderFood(@RequestParam("foodId") Long foodId, Model model) {
        Optional<Food> food = foodService.getFoodById(foodId);
        if (food.isPresent()) {
            model.addAttribute("food", food.get());
            return "food/food-book";
        }
        return "redirect:/food?error=notfound";
    }

    @Autowired
    private com.travel.unified.service.BookingService bookingService;

    @Autowired
    private com.travel.unified.service.UserService userService;

    @PostMapping("/book")
    public String processOrder(@RequestParam("foodId") Long foodId,
            @RequestParam("quantity") Integer quantity) {
        Optional<Food> foodOpt = foodService.getFoodById(foodId);
        if (foodOpt.isPresent()) {
            Food food = foodOpt.get();

            if (quantity <= 0 || quantity > food.getAvailableQuantity()) {
                return "redirect:/food/order?foodId=" + foodId + "&error=outofstock";
            }

            // Get current user
            org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder
                    .getContext().getAuthentication();
            com.travel.unified.model.User user = userService.findByEmail(auth.getName());

            if (user != null) {
                com.travel.unified.model.Booking booking = new com.travel.unified.model.Booking();
                booking.setUserId(user.getUserId());
                booking.setBookingType("FOOD");
                booking.setReferenceId(food.getFoodId());
                booking.setDate(java.time.LocalDate.now());
                booking.setStatus("CONFIRMED");

                bookingService.saveBooking(booking);

                int newQuantity = food.getAvailableQuantity() - quantity;
                food.setAvailableQuantity(newQuantity);
                foodService.saveFood(food);
            }
        }
        return "redirect:/bookings";
    }
}

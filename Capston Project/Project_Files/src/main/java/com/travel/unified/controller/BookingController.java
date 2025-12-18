package com.travel.unified.controller;

import com.travel.unified.model.Booking;
import com.travel.unified.model.User;
import com.travel.unified.service.BookingService;
import com.travel.unified.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getMyBookings(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findByEmail(email);

        if (user != null) {
            List<Booking> bookings = bookingService.getBookingsByUserId(user.getUserId());
            model.addAttribute("bookings", bookings);
        }

        return "booking/my-bookings";
    }
}

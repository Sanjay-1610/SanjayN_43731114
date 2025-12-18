package com.travel.unified.controller;

import com.travel.unified.model.Booking;
import com.travel.unified.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminBookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/bookings")
    public String showManageBookingsPage(Model model) {
        List<Booking> bookings = bookingService.getAllBookings(); // You might need to add this method to BookingService
        model.addAttribute("bookings", bookings);
        return "admin/manage-bookings";
    }

    @org.springframework.web.bind.annotation.PostMapping("/bookings/cancel")
    public String cancelBooking(@org.springframework.web.bind.annotation.RequestParam("bookingId") Long bookingId,
            @org.springframework.web.bind.annotation.RequestParam("reason") String reason) {
        bookingService.cancelBooking(bookingId, reason);
        return "redirect:/admin/bookings";
    }
}

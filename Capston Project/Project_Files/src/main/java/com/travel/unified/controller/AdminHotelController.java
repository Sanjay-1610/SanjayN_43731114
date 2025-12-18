package com.travel.unified.controller;

import com.travel.unified.model.Hotel;
import com.travel.unified.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminHotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/hotels")
    public String showManageHotelPage(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);
        return "admin/manage-hotels";
    }

    @PostMapping("/hotel/save")
    public String saveHotel(@ModelAttribute Hotel hotel) {
        hotelService.saveHotel(hotel);
        return "redirect:/admin/hotels";
    }

    @GetMapping("/hotel/delete/{id}")
    public String deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return "redirect:/admin/hotels";
    }
}

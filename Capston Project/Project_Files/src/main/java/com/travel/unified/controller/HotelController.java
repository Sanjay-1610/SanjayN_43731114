package com.travel.unified.controller;

import com.travel.unified.model.Hotel;
import com.travel.unified.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public String showHotelList(@RequestParam(required = false) String city, Model model) {
        List<Hotel> hotels;
        if (city != null && !city.isEmpty()) {
            hotels = hotelService.searchHotels(city);
        } else {
            hotels = hotelService.getAllHotels();
        }
        model.addAttribute("hotels", hotels);
        return "hotel/hotel-list";
    }

    @GetMapping("/book")
    public String bookHotel(@RequestParam("hotelId") Long hotelId, Model model) {
        Optional<Hotel> hotel = hotelService.getHotelById(hotelId);
        if (hotel.isPresent()) {
            model.addAttribute("hotel", hotel.get());
            return "hotel/room-book";
        }
        return "redirect:/hotels?error=notfound";
    }

    @Autowired
    private com.travel.unified.service.BookingService bookingService;

    @Autowired
    private com.travel.unified.service.UserService userService;

    @PostMapping("/book")
    public String processBooking(@RequestParam("hotelId") Long hotelId,
            @RequestParam("rooms") Integer rooms) {
        Optional<Hotel> hotelOpt = hotelService.getHotelById(hotelId);
        if (hotelOpt.isPresent()) {
            Hotel hotel = hotelOpt.get();

            if (rooms <= 0 || rooms > hotel.getAvailableRooms()) {
                return "redirect:/hotels/book?hotelId=" + hotelId + "&error=outofstock";
            }

            // Get current user
            org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder
                    .getContext().getAuthentication();
            com.travel.unified.model.User user = userService.findByEmail(auth.getName());

            if (user != null) {
                com.travel.unified.model.Booking booking = new com.travel.unified.model.Booking();
                booking.setUserId(user.getUserId());
                booking.setBookingType("HOTEL");
                booking.setReferenceId(hotel.getHotelId());
                booking.setDate(java.time.LocalDate.now());
                booking.setStatus("CONFIRMED");

                bookingService.saveBooking(booking);

                int newRooms = hotel.getAvailableRooms() - rooms;
                hotel.setAvailableRooms(newRooms);
                hotelService.saveHotel(hotel);
            }
        }
        return "redirect:/bookings";
    }
}

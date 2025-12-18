package com.travel.unified.controller;

import com.travel.unified.model.Transport;
import com.travel.unified.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/transport")
public class TransportController {

    @Autowired
    private TransportService transportService;

    @GetMapping
    public String showTransportList(@RequestParam(required = false) String source,
            @RequestParam(required = false) String destination,
            Model model) {
        List<Transport> transports;
        if (source != null && !source.isEmpty() && destination != null && !destination.isEmpty()) {
            transports = transportService.searchTransports(source, destination);
        } else {
            transports = transportService.getAllTransports();
        }
        model.addAttribute("transports", transports);
        return "transport/transport-list";
    }

    @GetMapping("/book")
    public String bookTransport(@RequestParam("transportId") Long transportId, Model model) {
        Optional<Transport> transport = transportService.getTransportById(transportId);
        if (transport.isPresent()) {
            model.addAttribute("transport", transport.get());
            return "transport/transport-book";
        }
        return "redirect:/transport?error=notfound";
    }

    @Autowired
    private com.travel.unified.service.BookingService bookingService;

    @Autowired
    private com.travel.unified.service.UserService userService;

    @PostMapping("/book")
    public String processBooking(@RequestParam("transportId") Long transportId,
            @RequestParam("seats") Integer seats) {
        Optional<Transport> transportOpt = transportService.getTransportById(transportId);
        if (transportOpt.isPresent()) {
            Transport transport = transportOpt.get();

            if (seats <= 0 || seats > transport.getAvailableSeats()) {
                return "redirect:/transport/book?transportId=" + transportId + "&error=outofstock";
            }

            // Get current user
            org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder
                    .getContext().getAuthentication();
            com.travel.unified.model.User user = userService.findByEmail(auth.getName());

            if (user != null) {
                com.travel.unified.model.Booking booking = new com.travel.unified.model.Booking();
                booking.setUserId(user.getUserId());
                booking.setBookingType("TRANSPORT");
                booking.setReferenceId(transport.getTransportId());
                booking.setDate(java.time.LocalDate.now());
                booking.setStatus("CONFIRMED");

                bookingService.saveBooking(booking);

                // Ideally decrement seats here
                int newSeats = transport.getAvailableSeats() - seats;
                transport.setAvailableSeats(newSeats);
                transportService.saveTransport(transport);
            }
        }
        return "redirect:/bookings";
    }
}

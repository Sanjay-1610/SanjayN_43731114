package com.travel.unified.controller;

import com.travel.unified.model.Transport;
import com.travel.unified.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminTransportController {

    @Autowired
    private TransportService transportService;

    @GetMapping("/transports")
    public String showManageTransportPage(Model model) {
        List<Transport> transports = transportService.getAllTransports();
        model.addAttribute("transports", transports);
        return "admin/manage-transport";
    }

    @PostMapping("/transport/save")
    public String saveTransport(@ModelAttribute Transport transport) {
        transportService.saveTransport(transport);
        return "redirect:/admin/transports";
    }

    @GetMapping("/transport/delete/{id}")
    public String deleteTransport(@PathVariable Long id) {
        transportService.deleteTransport(id);
        return "redirect:/admin/transports";
    }
}

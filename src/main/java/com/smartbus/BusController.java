package com.smartbus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BusController {

    @Autowired
    private BusRepository busRepository;

    // Save Bus
    @PostMapping("/saveBus")
    public String saveBus(Bus bus) {
        busRepository.save(bus);
        return "redirect:/";
    }

    // View All Buses
    @GetMapping("/viewBuses")
    public String viewBuses(Model model) {
        model.addAttribute("buses", busRepository.findAll());
        return "viewBuses";
    }

    // Delete Bus
    @GetMapping("/deleteBus/{id}")
    public String deleteBus(@PathVariable Long id) {
        busRepository.deleteById(id);
        return "redirect:/viewBuses";
    }

    // Update Bus Form
    @GetMapping("/editBus/{id}")
    public String editBus(@PathVariable Long id, Model model) {
        Bus bus = busRepository.findById(id).orElse(null);
        model.addAttribute("bus", bus);
        return "editBus";
    }

    // Update Bus
    @PostMapping("/updateBus")
    public String updateBus(Bus bus) {
        busRepository.save(bus);
        return "redirect:/viewBuses";
    }
}
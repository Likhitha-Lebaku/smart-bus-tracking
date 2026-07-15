package com.bus.smartbustracking.controller;

import com.bus.smartbustracking.entity.Bus;
import com.bus.smartbustracking.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusRepository busRepository;

    // Get all buses
    @GetMapping
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    // Add a new bus
    @PostMapping
    public Bus addBus(@RequestBody Bus bus) {
        return busRepository.save(bus);
    }

    // Update a bus
    @PutMapping("/{id}")
    public Bus updateBus(@PathVariable Long id, @RequestBody Bus updatedBus) {

        Bus bus = busRepository.findById(id).orElseThrow();

        bus.setBusNumber(updatedBus.getBusNumber());
        bus.setRoute(updatedBus.getRoute());
        bus.setCurrentLocation(updatedBus.getCurrentLocation());
        bus.setDestination(updatedBus.getDestination());
        bus.setStatus(updatedBus.getStatus());

        return busRepository.save(bus);
    }

    // Delete a bus
    @DeleteMapping("/{id}")
    public String deleteBus(@PathVariable Long id) {
        busRepository.deleteById(id);
        return "Bus deleted successfully";
    }
}
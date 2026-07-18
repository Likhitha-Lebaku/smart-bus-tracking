package com.smartbus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buses")
@CrossOrigin(origins = "*")
public class BusController {

    @Autowired
    private BusRepository busRepository;

    @GetMapping
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Bus> getBusById(@PathVariable Long id) {
        return busRepository.findById(id);
    }

    @PostMapping
    public Bus addBus(@RequestBody Bus bus) {
        return busRepository.save(bus);
    }

    @PutMapping("/{id}")
    public Bus updateBus(@PathVariable Long id, @RequestBody Bus updatedBus) {
        return busRepository.findById(id)
                .map(bus -> {
                    bus.setBusNumber(updatedBus.getBusNumber());
                    bus.setRoute(updatedBus.getRoute());
                    bus.setCurrentLocation(updatedBus.getCurrentLocation());
                    bus.setDestination(updatedBus.getDestination());
                    bus.setStatus(updatedBus.getStatus());
                    return busRepository.save(bus);
                })
                .orElseThrow(() -> new RuntimeException("Bus not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public String deleteBus(@PathVariable Long id) {
        busRepository.deleteById(id);
        return "Bus deleted successfully!";
    }
}
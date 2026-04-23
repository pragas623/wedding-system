package com.example.demo.controller;

import com.example.demo.model.HallBooking;
import com.example.demo.repository.HallRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/hall")
@CrossOrigin
public class HallController {

    private final HallRepository repo;

    public HallController(HallRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<HallBooking> all() {
        return repo.findAll();
    }

    @GetMapping("/user/{id}")
    public List<HallBooking> getByUser(@PathVariable Integer id) {
        return repo.findByUserId(id);
    }

    @GetMapping("/availability")
    public Object checkAvailability(@RequestParam String hallName, @RequestParam String bookingDate) {
        Map<String, Object> res = new HashMap<>();
        boolean available = !repo.existsByHallNameAndBookingDate(hallName, bookingDate);
        res.put("available", available);
        res.put("message", available ? "Hall is available" : "Hall is already booked");
        return res;
    }

    @PostMapping
    public Object save(@RequestBody HallBooking h) {
        Map<String, Object> res = new HashMap<>();

        if (h.getCustomerName() == null || h.getCustomerName().trim().isEmpty()) {
            res.put("error", "Customer name is required");
            return res;
        }

        if (h.getHallName() == null || h.getHallName().trim().isEmpty()) {
            res.put("error", "Hall name is required");
            return res;
        }

        if (h.getBookingDate() == null || h.getBookingDate().trim().isEmpty()) {
            res.put("error", "Booking date is required");
            return res;
        }

        if (LocalDate.parse(h.getBookingDate()).isBefore(LocalDate.now())) {
            res.put("error", "Booking date cannot be in the past");
            return res;
        }

        if (h.getGuestCount() <= 0) {
            res.put("error", "Guest count must be greater than 0");
            return res;
        }

        if (h.getUserId() == null) {
            res.put("error", "User ID is required");
            return res;
        }

        if (repo.existsByHallNameAndBookingDate(h.getHallName(), h.getBookingDate())) {
            res.put("error", "Hall already booked for this date");
            return res;
        }

        if (h.getStatus() == null || h.getStatus().trim().isEmpty()) {
            h.setStatus("Pending");
        }

        HallBooking saved = repo.save(h);
        res.put("message", "Booking added successfully");
        res.put("data", saved);
        return res;
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id, @RequestBody HallBooking h) {
        Map<String, Object> res = new HashMap<>();

        HallBooking existing = repo.findById(id).orElse(null);
        if (existing == null) {
            res.put("error", "Booking not found");
            return res;
        }

        if (h.getCustomerName() == null || h.getCustomerName().trim().isEmpty()) {
            res.put("error", "Customer name is required");
            return res;
        }

        if (h.getHallName() == null || h.getHallName().trim().isEmpty()) {
            res.put("error", "Hall name is required");
            return res;
        }

        if (h.getBookingDate() == null || h.getBookingDate().trim().isEmpty()) {
            res.put("error", "Booking date is required");
            return res;
        }

        if (h.getGuestCount() <= 0) {
            res.put("error", "Guest count must be greater than 0");
            return res;
        }

        Optional<HallBooking> conflict = repo.findByHallNameAndBookingDate(h.getHallName(), h.getBookingDate());
        if (conflict.isPresent() && !conflict.get().getId().equals(id)) {
            res.put("error", "Another booking already exists for this hall and date");
            return res;
        }

        existing.setCustomerName(h.getCustomerName());
        existing.setHallName(h.getHallName());
        existing.setBookingDate(h.getBookingDate());
        existing.setGuestCount(h.getGuestCount());
        existing.setStatus(h.getStatus());

        HallBooking updated = repo.save(existing);
        res.put("message", "Booking updated successfully");
        res.put("data", updated);
        return res;
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {
        Map<String, Object> res = new HashMap<>();

        HallBooking existing = repo.findById(id).orElse(null);
        if (existing == null) {
            res.put("error", "Booking not found");
            return res;
        }

        repo.deleteById(id);
        res.put("message", "Booking deleted successfully");
        return res;
    }
}
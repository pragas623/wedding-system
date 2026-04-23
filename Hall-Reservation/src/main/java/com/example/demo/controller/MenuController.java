package com.example.demo.controller;

import com.example.demo.model.MenuPackage;
import com.example.demo.repository.MenuRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
@CrossOrigin
public class MenuController {

    private final MenuRepository repo;

    public MenuController(MenuRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<MenuPackage> all() {
        return repo.findAll();
    }

    @PostMapping
    public Object save(@RequestBody MenuPackage m) {
        Map<String, Object> res = new HashMap<>();

        if (m.getMenuName() == null || m.getMenuName().trim().isEmpty()) {
            res.put("error", "Menu name is required");
            return res;
        }

        if (m.getFoodItems() == null || m.getFoodItems().trim().isEmpty()) {
            res.put("error", "Food items are required");
            return res;
        }

        if (m.getPricePerPerson() <= 0) {
            res.put("error", "Price must be greater than 0");
            return res;
        }

        if (m.getStatus() == null || m.getStatus().trim().isEmpty()) {
            m.setStatus("Available");
        }

        MenuPackage saved = repo.save(m);
        res.put("message", "Menu added successfully");
        res.put("data", saved);
        return res;
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id, @RequestBody MenuPackage m) {
        Map<String, Object> res = new HashMap<>();

        MenuPackage existing = repo.findById(id).orElse(null);
        if (existing == null) {
            res.put("error", "Menu not found");
            return res;
        }

        if (m.getMenuName() == null || m.getMenuName().trim().isEmpty()) {
            res.put("error", "Menu name is required");
            return res;
        }

        if (m.getFoodItems() == null || m.getFoodItems().trim().isEmpty()) {
            res.put("error", "Food items are required");
            return res;
        }

        if (m.getPricePerPerson() <= 0) {
            res.put("error", "Price must be greater than 0");
            return res;
        }

        existing.setMenuName(m.getMenuName());
        existing.setFoodItems(m.getFoodItems());
        existing.setPricePerPerson(m.getPricePerPerson());
        existing.setDescription(m.getDescription());
        existing.setStatus(m.getStatus());

        MenuPackage updated = repo.save(existing);
        res.put("message", "Menu updated successfully");
        res.put("data", updated);
        return res;
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {
        Map<String, Object> res = new HashMap<>();

        MenuPackage existing = repo.findById(id).orElse(null);
        if (existing == null) {
            res.put("error", "Menu not found");
            return res;
        }

        repo.deleteById(id);
        res.put("message", "Menu deleted successfully");
        return res;
    }
}
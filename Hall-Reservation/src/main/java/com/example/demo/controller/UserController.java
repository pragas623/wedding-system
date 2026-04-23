package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/register")
    public Object register(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();

        if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
            res.put("error", "Full name is required");
            return res;
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            res.put("error", "Email is required");
            return res;
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            res.put("error", "Password is required");
            return res;
        }

        if (repo.findByEmail(user.getEmail()).isPresent()) {
            res.put("error", "Email already exists");
            return res;
        }

        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("CLIENT");
        }

        repo.save(user);
        res.put("message", "User registered successfully");
        return res;
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();

        User existing = repo.findByEmail(user.getEmail()).orElse(null);
        if (existing == null) {
            res.put("error", "Invalid email");
            return res;
        }

        if (!existing.getPassword().equals(user.getPassword())) {
            res.put("error", "Wrong password");
            return res;
        }

        res.put("user", existing);
        return res;
    }

    @GetMapping
    public List<User> getAll() {
        return repo.findAll();
    }
}
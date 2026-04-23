package com.example.demo.repository;

import com.example.demo.model.MenuPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuPackage, Integer> {
}
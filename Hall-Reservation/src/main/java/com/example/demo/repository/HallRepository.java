package com.example.demo.repository;

import com.example.demo.model.HallBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HallRepository extends JpaRepository<HallBooking, Integer> {
    List<HallBooking> findByUserId(Integer userId);
    boolean existsByHallNameAndBookingDate(String hallName, String bookingDate);
    Optional<HallBooking> findByHallNameAndBookingDate(String hallName, String bookingDate);
}
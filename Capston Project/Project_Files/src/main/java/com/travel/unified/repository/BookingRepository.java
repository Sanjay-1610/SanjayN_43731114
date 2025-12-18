package com.travel.unified.repository;

import com.travel.unified.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    void deleteByUserId(Long userId);

    java.util.List<Booking> findByUserId(Long userId);
}

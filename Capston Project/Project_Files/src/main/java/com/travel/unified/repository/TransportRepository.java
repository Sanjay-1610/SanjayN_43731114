package com.travel.unified.repository;

import com.travel.unified.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
    List<Transport> findBySourceAndDestination(String source, String destination);
}

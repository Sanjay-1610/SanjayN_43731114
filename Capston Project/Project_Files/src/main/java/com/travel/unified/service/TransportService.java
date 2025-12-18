package com.travel.unified.service;

import com.travel.unified.model.Transport;
import com.travel.unified.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportService {

    @Autowired
    private TransportRepository transportRepository;

    public List<Transport> getAllTransports() {
        return transportRepository.findAll();
    }

    public List<Transport> searchTransports(String source, String destination) {
        return transportRepository.findBySourceAndDestination(source, destination);
    }

    public Optional<Transport> getTransportById(Long id) {
        return transportRepository.findById(id);
    }

    public void saveTransport(Transport transport) {
        transportRepository.save(transport);
    }

    public void deleteTransport(Long id) {
        transportRepository.deleteById(id);
    }
}

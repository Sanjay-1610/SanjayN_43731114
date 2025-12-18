package com.travel.unified.service;

import com.travel.unified.model.Hotel;
import com.travel.unified.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public List<Hotel> searchHotels(String city) {
        return hotelRepository.findByCityContainingIgnoreCase(city);
    }

    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    public void saveHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}

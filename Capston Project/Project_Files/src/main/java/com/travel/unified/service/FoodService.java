package com.travel.unified.service;

import com.travel.unified.model.Food;
import com.travel.unified.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    public List<Food> searchFood(String city, String type) {
        if (type != null && !type.equals("All Types") && city != null && !city.isEmpty()) {
            return foodRepository.findByCityAndType(city, type);
        } else if (city != null && !city.isEmpty()) {
            return foodRepository.findByCity(city);
        }
        return foodRepository.findAll();
    }

    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    public void saveFood(Food food) {
        foodRepository.save(food);
    }

    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }
}

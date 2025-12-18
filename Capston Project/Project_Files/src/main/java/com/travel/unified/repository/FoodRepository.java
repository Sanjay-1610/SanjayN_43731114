package com.travel.unified.repository;

import com.travel.unified.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByCityAndType(String city, String type);

    List<Food> findByCity(String city);
}

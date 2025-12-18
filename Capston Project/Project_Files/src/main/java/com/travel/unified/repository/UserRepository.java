package com.travel.unified.repository;

import com.travel.unified.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    java.util.Optional<User> findByEmail(String email);
}

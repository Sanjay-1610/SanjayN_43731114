package com.travel.unified.service;

import com.travel.unified.model.User;
import com.travel.unified.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@jakarta.transaction.Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private com.travel.unified.repository.RoleRepository roleRepository;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Autowired
    private com.travel.unified.repository.BookingRepository bookingRepository;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign default role
        com.travel.unified.model.Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.setRoles(java.util.Collections.singleton(userRole));

        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Clear roles to remove entries from user_roles table (FK constraint)
        user.getRoles().clear();
        userRepository.save(user);

        // Delete associated bookings
        bookingRepository.deleteByUserId(id);

        // Delete the user
        userRepository.delete(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public java.util.List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

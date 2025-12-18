package com.travel.unified.service;

import com.travel.unified.model.User;
import com.travel.unified.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new UsernameNotFoundException(
                                                "User not found with email: " + email));

                return new org.springframework.security.core.userdetails.User(
                                user.getEmail(),
                                user.getPassword(),
                                user.getRoles().stream()
                                                .map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority(
                                                                "ROLE_" + role.getName()))
                                                .collect(java.util.stream.Collectors.toList()));
        }
}

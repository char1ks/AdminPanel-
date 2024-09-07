package com.example.CookiesPractice.service;

import com.example.CookiesPractice.model.Admin;
import com.example.CookiesPractice.repository.AdminRepository;
import com.example.CookiesPractice.security.AdminDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = Optional.ofNullable(adminRepository.findByLogin(username));
        if (adminOptional.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new AdminDetails(adminOptional.get());
    }
}
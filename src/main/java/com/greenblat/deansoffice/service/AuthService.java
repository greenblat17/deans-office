package com.greenblat.deansoffice.service;

import com.greenblat.deansoffice.exception.ResourceAlreadyExistsException;
import com.greenblat.deansoffice.exception.ResourceNotFoundException;
import com.greenblat.deansoffice.model.Role;
import com.greenblat.deansoffice.model.User;
import com.greenblat.deansoffice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public void authenticate(String username, String password) {
        var request = new UsernamePasswordAuthenticationToken(username, password);
        Authentication result = authenticationManager.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(result);
    }

    public void registration(User user) {
        userRepository.findByUsername(user.getUsername())
                .ifPresent(u -> {
                    throw new ResourceAlreadyExistsException("User already exists");
                });
        userRepository.save(user);
    }

    public void addAdmin(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }
}

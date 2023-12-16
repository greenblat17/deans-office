package com.greenblat.deansoffice.service;

import com.greenblat.deansoffice.dto.AuthRequest;
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

    public void authenticate(AuthRequest request) {
        var authToken = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        Authentication result = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(result);
    }

    public void registration(AuthRequest request) {
        userRepository.findByUsername(request.username())
                .ifPresent(u -> {
                    throw new ResourceAlreadyExistsException("User already exists");
                });
        var user = buildUserFromRequest(request);
        userRepository.save(user);
    }

    public void addAdmin(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    private static User buildUserFromRequest(AuthRequest request) {
        return User.builder()
                .username(request.username())
                .password(request.password())
                .role(Role.USER)
                .build();
    }
}

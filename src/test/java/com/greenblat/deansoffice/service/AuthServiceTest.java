package com.greenblat.deansoffice.service;

import com.greenblat.deansoffice.dto.AuthRequest;
import com.greenblat.deansoffice.exception.ResourceAlreadyExistsException;
import com.greenblat.deansoffice.exception.ResourceNotFoundException;
import com.greenblat.deansoffice.model.Role;
import com.greenblat.deansoffice.model.User;
import com.greenblat.deansoffice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    @Test
    void authenticateValidCredentials() {
        var authRequest = buildAuthRequest();
        var authToken = new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password());
        var authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(authToken)).thenReturn(authentication);

        authService.authenticate(authRequest);

        verify(authenticationManager).authenticate(authToken);
        assertEquals(authentication, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void registrationNewUserSuccessfully() {
        var authRequest = buildAuthRequest();
        var user = buildUserFromAuthRequest(authRequest);

        when(userRepository.findByUsername(authRequest.username())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        assertDoesNotThrow(() -> authService.registration(authRequest));

        verify(userRepository).findByUsername(authRequest.username());
        verify(userRepository).save(user);
    }

    @Test
    void registrationExistingUserThrowException() {
        var authRequest = buildAuthRequest();
        var existingUser = buildUserFromAuthRequest(authRequest);

        when(userRepository.findByUsername(authRequest.username())).thenReturn(Optional.of(existingUser));

        assertThrows(ResourceAlreadyExistsException.class, () -> authService.registration(authRequest));

        verify(userRepository).findByUsername(authRequest.username());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void addAdminWhenUserExists() {
        String username = "username";
        User user = User.builder()
                .username(username)
                .role(Role.USER)
                .build();

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        assertDoesNotThrow(() -> authService.addAdmin(username));

        verify(userRepository).findByUsername(username);
        verify(userRepository).save(user);

        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    void addAdminWhenUserNotExistsThrowException() {
        String username = "username";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authService.addAdmin(username));

        verify(userRepository).findByUsername(username);
        verify(userRepository, never()).save(any(User.class));
    }

    private AuthRequest buildAuthRequest() {
        return new AuthRequest("name", "pass", Role.USER);
    }

    private User buildUserFromAuthRequest(AuthRequest authRequest) {
        return User.builder()
                .username(authRequest.username())
                .password(authRequest.password())
                .role(Role.USER)
                .build();
    }
}
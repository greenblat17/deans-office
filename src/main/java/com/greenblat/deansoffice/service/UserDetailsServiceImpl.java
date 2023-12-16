package com.greenblat.deansoffice.service;

import com.greenblat.deansoffice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var userBuilder = User.withUsername(username)
                .password(new BCryptPasswordEncoder().encode(user.getPassword()));
        if (user.isSuperuser()) {
            userBuilder.roles("USER", "ADMIN");
        } else {
            userBuilder.roles("USER");
        }
        return userBuilder.build();
    }
}

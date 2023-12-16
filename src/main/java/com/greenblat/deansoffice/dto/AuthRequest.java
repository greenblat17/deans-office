package com.greenblat.deansoffice.dto;

import com.greenblat.deansoffice.model.Role;

public record AuthRequest(String username,
                         String password,
                         Role role) {
}

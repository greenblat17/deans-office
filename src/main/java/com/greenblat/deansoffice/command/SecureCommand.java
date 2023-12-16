package com.greenblat.deansoffice.command;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.shell.Availability;

public abstract class SecureCommand {

    public Availability isUserSignedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
            return Availability.unavailable("you are not signedIn. Please sign in to be able to use this command!");
        }
        return Availability.available();
    }

    public Availability isUserAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
            return Availability.unavailable("you are not signedIn. Please sign in to be able to use this command!");
        }
        if (!authentication.getAuthorities().contains("ROLE_ADMIN")) {
            return Availability.unavailable("you have insufficient privileges to run this command!");
        }
        return Availability.available();
    }

}

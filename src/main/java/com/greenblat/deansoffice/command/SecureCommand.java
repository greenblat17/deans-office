package com.greenblat.deansoffice.command;

import org.springframework.shell.Availability;

public abstract class SecureCommand {

    public Availability isUserSignedIn() {
        return Availability.unavailable("You are not signedIn. Please sign in to be able to use this command!");
    }

}

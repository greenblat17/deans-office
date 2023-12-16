package com.greenblat.deansoffice.command;

import com.greenblat.deansoffice.dto.AuthRequest;
import com.greenblat.deansoffice.model.Role;
import com.greenblat.deansoffice.service.AuthService;
import com.greenblat.deansoffice.util.InputReader;
import com.greenblat.deansoffice.util.ShellHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.util.StringUtils;


@ShellComponent
@RequiredArgsConstructor
public class AuthCommand extends SecureCommand{

    private final AuthService authService;

    @Lazy
    @Autowired
    private ShellHelper shellHelper;

    @Lazy
    @Autowired
    private InputReader inputReader;

    @ShellMethod(key = "sign-in", value = "Sign In as user")
    public void signIn() {
        String username = readUsername();
        String password = inputReader.prompt("Please enter your password", null, false);

        try {
            var user = new AuthRequest(username, password, Role.USER);
            authService.authenticate(user);
            shellHelper.printSuccess("Credentials successfully authenticated! " + username + " -> welcome to dean's office application.");
        } catch (AuthenticationException e) {
            shellHelper.printWarning("Authentication failed: " + e.getMessage());
        }
    }

    @ShellMethod(key = "sign-up", value = "Sign Up as user")
    public void signUp() {
        String username = readUsername();
        String password = inputReader.prompt("Please enter your password", null, false);

        var user = new AuthRequest(username, password, Role.USER);
        authService.registration(user);

        shellHelper.printSuccess("Registration was successfully!");
    }

    @ShellMethod(key = "admin", value = "Became an admin")
    @ShellMethodAvailability("isUserAdmin")
    public void becomeAdmin() {
        String username = readUsername();
        authService.addAdmin(username);
        shellHelper.printSuccess("User is admin now");
    }

    private String readUsername() {
        String username;
        boolean usernameInvalid = true;
        do {
            username = inputReader.prompt("Please enter your username");
            if (StringUtils.hasText(username)) {
                usernameInvalid = false;
            } else {
                shellHelper.printWarning("Username can not be empty string!");
            }
        } while (usernameInvalid);
        return username;
    }
}

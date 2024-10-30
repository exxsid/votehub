package com.lacortezdev.onlinevotingsystem.auth;

import com.lacortezdev.onlinevotingsystem.jwt.JwtService;
import com.lacortezdev.onlinevotingsystem.security.UserRole;
import com.lacortezdev.onlinevotingsystem.user.User;
import com.lacortezdev.onlinevotingsystem.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationController(UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("login")
    public ResponseEntity<String> login(
            @RequestParam Long id,
            @RequestParam String password,
            HttpServletResponse response) {
        User user = userService.loadUserByUsername(id.toString());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean isPasswordMatched = passwordEncoder.matches(password, user.getPassword());

        if (!isPasswordMatched) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            String token = jwtService.generateToken(user);

            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(1800);
            response.addCookie(cookie);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseEntity<?> signup(
            @RequestBody SignupUserRequestBody userRequestBody
    ) {
        String encodedPassword = passwordEncoder.encode(userRequestBody.password);

        User newUser = User.builder()
                .firstName(userRequestBody.firstName())
                .middleName(userRequestBody.middleName())
                .lastName(userRequestBody.lastName())
                .password(encodedPassword)
                .roles(UserRole.VOTER)
                .salt("")
                .isActive(true)
                .build();

        User user = userService.saveNewUser(newUser);

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid id or password");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Success");

    }

    record SignupUserRequestBody(
            String firstName,
            String middleName,
            String lastName,
            String password
    ) {
    }
}

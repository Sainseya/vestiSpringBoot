package com.example.vestiback.controller;

import com.example.vestiback.model.ERole;
import com.example.vestiback.model.Role;
import com.example.vestiback.model.User;
import com.example.vestiback.payload.request.LoginRequest;
import com.example.vestiback.payload.request.SignupRequest;
import com.example.vestiback.payload.response.MessageResponse;
import com.example.vestiback.repository.UserRepository;
import com.example.vestiback.repository.RoleRepository;
import com.example.vestiback.security.jwt.JwtUtils;
import com.example.vestiback.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vesti/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    private final AuthService authService;

    public AuthController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder encoder,
            JwtUtils jwtUtils, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return (ResponseEntity<?>) authService.signin(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.signup(signUpRequest);
    }
}

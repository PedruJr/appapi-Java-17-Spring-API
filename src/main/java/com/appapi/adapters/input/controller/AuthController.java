package com.appapi.adapters.input.controller;

import com.appapi.adapters.input.controller.dto.CreateUserDTO;
import com.appapi.adapters.input.controller.dto.LoginRequestDTO;
import com.appapi.adapters.input.controller.dto.LoginResponseDTO;
import com.appapi.application.services.UserService;
import com.appapi.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.appapi.infrastructure.security.JwtTokenProvider;


import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody CreateUserDTO createUserDTO) {
        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setPassword(createUserDTO.getPassword());
        user.setEmail(createUserDTO.getEmail());
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<User> userOptional = userService.findUserByUsername(loginRequestDTO.getUsername());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(401).build();
        }

        User user = userOptional.get();

        if (!user.getPassword().equals(loginRequestDTO.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        String token = jwtTokenProvider.generateToken(user.getUsername());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @GetMapping("/protected")
    public ResponseEntity<String> protectedEndpoint() {
        return ResponseEntity.ok("✅ Você acessou uma rota protegida com sucesso!");
    }
}

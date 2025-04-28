package com.appapi.adapters.input.controller;

import com.appapi.adapters.input.controller.dto.CreateUserDTO;
import com.appapi.adapters.input.controller.dto.LoginRequestDTO;
import com.appapi.application.services.UserService;
import com.appapi.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
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
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<User> userOptional = userService.findUserByUsername(loginRequestDTO.getUsername());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(401).body("Usuário não encontrado ou senha inválida");
        }

        User user = userOptional.get();

        if (!user.getPassword().equals(loginRequestDTO.getPassword())) {
            return ResponseEntity.status(401).body("Usuário senha inválida");
        }

        // Aqui ainda vamos gerar o JWT na próxima etapa
        return ResponseEntity.ok("Login realizado com sucesso! (JWT será gerado aqui)");
    }
}

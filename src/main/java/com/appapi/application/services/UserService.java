package com.appapi.application.services;

import com.appapi.domain.exceptions.EntityNotFoundException;
import com.appapi.domain.models.User;
import com.appapi.domain.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado"));
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com username '" + username + "' não encontrado"));
    }

    public List<User> listAllUsers() {
        return userRepository.findAll();
    }
}

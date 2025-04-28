package com.appapi.domain.repositories;

import com.appapi.domain.models.User;
import java.util.Optional;
import java.util.List;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    List<User> findAll();
}

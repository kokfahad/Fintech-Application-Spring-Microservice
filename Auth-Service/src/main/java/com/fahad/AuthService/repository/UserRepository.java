package com.fahad.AuthService.repository;

import com.fahad.AuthService.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

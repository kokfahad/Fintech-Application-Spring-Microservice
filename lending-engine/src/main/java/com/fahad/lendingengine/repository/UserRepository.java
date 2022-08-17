package com.fahad.lendingengine.repository;

import com.fahad.lendingengine.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

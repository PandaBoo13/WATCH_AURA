package com.example.WATCH_AURA.repository;

import com.example.WATCH_AURA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

package com.jwt_security.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt_security.app.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


}

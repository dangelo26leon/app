package com.project.app.services;

import java.util.List;

import com.project.app.dto.LoginDto;
import com.project.app.models.User;

public interface UserService {
    public String login(LoginDto loginDto);
    public User getUsuario();

    User createUser(User user);
    User updateUser(User user);
    User getUserById(Long Id);
    List<User> getAllUsers();
    void deleteUser(Long id);

}

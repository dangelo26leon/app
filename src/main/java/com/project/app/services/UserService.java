package com.project.app.services;

import com.project.app.dto.LoginDto;
import com.project.app.models.User;

public interface UserService {
    public String login(LoginDto loginDto);
    public User getUsuario();

}

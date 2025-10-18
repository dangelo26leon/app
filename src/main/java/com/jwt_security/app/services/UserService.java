package com.jwt_security.app.services;

import com.jwt_security.app.dto.LoginDto;
import com.jwt_security.app.models.User;

public interface UserService {
    public String login(LoginDto loginDto);
    public User getUsuario();

}

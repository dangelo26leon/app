package com.project.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.app.config.JwtTokenService;
import com.project.app.dto.LoginDto;
import com.project.app.models.User;
import com.project.app.repository.UserRepository;



@Service(value = "userService")
public class UserServiceImpl implements UserService { 

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    public String login(LoginDto loginDto) {
        final Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                                                        loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userRepository.findByUsername(loginDto.getUsername());

        return jwtTokenService.generateToken(user.getUsername(), user.getRoles());
    }

    @Override
    public User getUsuario() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername());
    }

    
    

}

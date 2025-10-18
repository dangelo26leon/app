package com.project.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.models.User;
import com.project.app.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    
    @GetMapping
    public ResponseEntity<User> userProfile(){
        return ResponseEntity.ok(userService.getUsuario());
    }

}

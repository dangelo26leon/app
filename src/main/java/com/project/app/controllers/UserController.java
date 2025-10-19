package com.project.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.models.User;
import com.project.app.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    
    @GetMapping
    public ResponseEntity<User> userProfile(){
        return ResponseEntity.ok(userService.getUsuario());
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getAuthenticatedUserProfile() {
        // Utiliza el método que ya tenías para obtener el usuario del SecurityContext
        return ResponseEntity.ok(userService.getUsuario()); 
    }

    // 2. CREAR USUARIO (Registro Público)
    // Este endpoint es ideal para un formulario de registro público (no requiere token ni rol ADMIN)
    @PostMapping("/register") 
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // 3. OBTENER TODOS LOS USUARIOS (Protegido: Solo ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // 4. OBTENER USUARIO POR ID (Protegido: Solo ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // 5. ACTUALIZAR USUARIO (Protegido: Solo ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    // 6. ELIMINAR USUARIO (Protegido: Solo ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }



}

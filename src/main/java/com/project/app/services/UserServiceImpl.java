package com.project.app.services;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.app.config.JwtTokenService;
import com.project.app.dto.LoginDto;
import com.project.app.dto.AuthResponse;
import com.project.app.models.TipoRol;
import com.project.app.models.User;
import com.project.app.models.Rol;
import com.project.app.repository.RolRepository;
import com.project.app.repository.UserRepository;



@Service(value = "userService")
public class UserServiceImpl implements UserService { 

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    public AuthResponse login(LoginDto loginDto) {
        final Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                                                        loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userRepository.findByUsername(loginDto.getUsername());

        String token = jwtTokenService.generateToken(user.getUsername(), user.getRoles());
        
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setUsername(user.getUsername());
        response.setMessage("Login exitoso");
        
        return response;
    }

    @Override
    public User getUsuario() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername());
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Rol defaultRol = rolRepository.findByRol(TipoRol.USER)
        .orElseThrow(() -> new RuntimeException("Error: Rol 'USER' no encontrado. Asegúrese de inicializar los roles."));
        
        Set<Rol> roles = new HashSet<>();
        roles.add(defaultRol);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + user.getId()));
        
        existingUser.setUsername(user.getUsername());
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        //Encriptar la contraseña SOLO si se proporciona en la petición PUT
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        User updateUser = userRepository.save(existingUser);
        return updateUser;

    }

    @Override
    public User getUserById(Long Id) {
        Optional<User> optionalUser = userRepository.findById(Id);
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();  
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

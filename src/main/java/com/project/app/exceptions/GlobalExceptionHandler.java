package com.project.app.exceptions;

import java.security.SignatureException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleTokenExpired(ExpiredJwtException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("El token ha expirado, por favor inicia sesión de nuevo.");
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> handleSignature(SignatureException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token inválido o modificado.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error: " + e.getMessage());
    }

}

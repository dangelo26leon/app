package com.project.app.config;

import java.util.Date;
import java.util.Set;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.project.app.models.Rol;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtTokenService {

    private String secretKey = "NllmZHptNVVrNG9RRUs3NllmZHptNVVrNG9RRUs3NllmZHptNVVrNG9RRUs3NllmZHptNVVrNG9RRUs3NllmZHptNVVrNG9RRUs3NllmZHptNVVrNG9RRUs3NllmZHptNVVrNG9RRUs3Nl";
    private static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;

     public String generateToken(String username, Set<Rol> authorities) {
        return Jwts.builder().subject(username)
                .claim("roles", authorities)//can also set a map
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .issuer("localhost:8080")
                .signWith(getSecretKey(), Jwts.SIG.HS512)
                .compact();
    }

    public String extractUsernameFromToken(String token) {
        if (isTokenExpired(token)) {
            return null;
        }
        return getClaims(token, Claims::getSubject);
    }

    public <T> T getClaims(String token, Function<Claims, T> resolver) {
        return resolver.apply(Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenExpired(String token) {
        Date expiration = getClaims(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}

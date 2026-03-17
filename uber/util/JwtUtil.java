package com.project.uber.util;


import com.project.uber.entity.User;
import com.project.uber.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {
    private final String secretKey = "mySuperSecretKeyForJwtAuthenticationThatIsAtLeast32CharactersLong";
    private  final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    private  final long EXPIRATION = 1000*60*60;
    @Autowired
    UserRepository userRepository;

    public String generateToken(String username) {
       User user = userRepository.findByEmail(username);

       return Jwts.builder()
               .setSubject(user.getEmail())
               .claim("roles", user.getRole().getRoleName())
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
               .signWith(key,SignatureAlgorithm.HS256)
               .compact();
    }
    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }
    public String extractRole(String token){
        return (String) extractClaims(token).get("roles");
    }

    private boolean isTokenExpired(String token){
        return extractClaims(token)
                .getExpiration()
                .before(new Date());
    }


    private Claims extractClaims(String  token){
        return Jwts .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token) // payload nikalenga
                .getBody();
    }
}

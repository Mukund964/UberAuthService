package org.example.authservice.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class jwtService implements CommandLineRunner {

    @Value("${jwt.expiry}")
    private int expiry;
    @Value("${jwt.SECRET_KEY}")
    private String SECRET_KEY;


    private String tokenCreation(Map<String, Object> payload, String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expiry);

        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiration)
                .subject(username)
                .signWith(key)
                .compact();

    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> mp = new HashMap<>();
        mp.put("username", "admin");
        mp.put("password", "admin");
        String result = tokenCreation(mp, "admin");
        System.out.println(result);
    }
}

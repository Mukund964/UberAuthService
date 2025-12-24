package org.example.authservice.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements CommandLineRunner {

    @Value("${jwt.expiry}")
    private int expiry;
    @Value("${jwt.SECRET_KEY}")
    private String SECRET_KEY;


    public String tokenCreation(Map<String, Object> payload, String username) {
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
    public String tokenCreationWithoutMap(String username){
        Map<String, Object> payload = new HashMap<>();
        return tokenCreation(payload,username);
    }
    private Claims extractAllPayload(String token) {
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsExtractor) {
            final Claims claim = extractAllPayload(token);
            return claimsExtractor.apply(claim);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public Boolean isTokenValid(String token, String email) {
        final String emailFetchedFromToken = extractEmail(token);
        return emailFetchedFromToken.equals(email);
    }

    public String extractEmail(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Object extractPayload(String token, String payloadKey) {
        Claims claim = extractAllPayload(token);
        return  claim.get(payloadKey);
    }


    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> mp = new HashMap<>();
        mp.put("email", "a@b.com");
        mp.put("phoneNumber", "9999999999");
//        String result = tokenCreation(mp, "abc");
//        System.out.println("Generated token is: " + result);
//        System.out.println(extractPayload(result, "email").toString());
    }
}

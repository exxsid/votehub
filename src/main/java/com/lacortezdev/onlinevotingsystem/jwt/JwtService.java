package com.lacortezdev.onlinevotingsystem.jwt;

import com.lacortezdev.onlinevotingsystem.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-day}")
    private long jwtExpiration;

    public String generateToken(User user) throws NoSuchAlgorithmException {
        Map<String, Object> content = new HashMap<>();
        byte[] userByteArray = SerializationUtils.serialize(user);
        content.put("user", userByteArray);
        String jwt = Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .subject(user.getUsername())
                .signWith(getSecretKey())
                .expiration(Date.from(
                                LocalDateTime.now()
                                        .plusDays(jwtExpiration)
                                        .atZone(ZoneId.systemDefault())
                                        .toInstant()
                        )
                )
                .compact();
        return jwt;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, User user) {
        String username = extractUsername(token);
        Date expiredTime = extractClaim(token, Claims::getExpiration);

        boolean res = (username.equals(user.getUsername())) && !expiredTime.before(new Date());
        return res;
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsTFunction.apply(claims);
    }

    private SecretKey getSecretKey() {
        byte[] encoded = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(encoded);
    }
}


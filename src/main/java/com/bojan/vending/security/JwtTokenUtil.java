package com.bojan.vending.security;


import com.bojan.vending.model.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Level;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
@Log
public class JwtTokenUtil {

    private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";
    private final String jwtIssuer = "example.io";

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(format("%s,%s", user.getId(), user.getUsername()))
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[0];
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.log(Level.WARNING, "Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.log(Level.WARNING,"Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.log(Level.WARNING,"Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.log(Level.WARNING,"Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.log(Level.WARNING,"JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

}

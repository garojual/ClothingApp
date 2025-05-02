package com.jag.clothingApp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${JWT_SECRET}")
    private String JWT_SECRET;

    /*
    * Create a JWT token with the username as a subject, an emission date and an expiration date
    * the token is signed with an automatic encryption system based on the length of the JWT_SECRET
    * */
    public String generateToken(UserDetails userDetails){

        Date now = new Date();

        Long JWT_EXPIRATION = 604800000L; //7 Days
        Date expiration = new Date(now.getTime() + JWT_EXPIRATION);

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder().subject(userDetails.getUsername())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(key)
                .compact();
    }

    /*
    * Analize the token, valide it with the JWT_SECRET and extracts the subject
    * */
    public String getTokenEmail(String token){
        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /*
    * Tries to validate the signature and structure of the token, if valid it returns true, otherwise returns false
    * which can indicate a manipulated, invalid or expired token
    * */
    public boolean validateToken(String token){
        try {
            SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        }catch (Exception e){
            return  false;
        }
    }
}

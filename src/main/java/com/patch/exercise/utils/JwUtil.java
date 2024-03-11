package com.patch.exercise.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class JwUtil {
    public String gerarToken(UserDetails userDetails){
        return Jwts.builder().issuer("WEG")
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 300000))
                .signWith(SignatureAlgorithm.NONE, "senha123")
                .subject(userDetails.getUsername())
                .compact();

    }

    public void validarToken(String token){
        var parse = Jwts.parser().setSigningKey("senha123").build();
        parse.parseSignedClaims(token);
    }

    public String getUsername(String token){
        return getParser().parseSignedClaims(token).getPayload().
    }

    private JwtParser getParser(){
        return Jwts.parser().setSigningKey("senha123")
                .build();
    }
}

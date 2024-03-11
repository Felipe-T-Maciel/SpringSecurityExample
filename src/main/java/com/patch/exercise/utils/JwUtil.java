package com.patch.exercise.utils;

import com.patch.exercise.service.AutenticacaoSerivce;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

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

    private Jws<Claims> validarToken(String token){
        return getParser().parseSignedClaims(token);
    }

    public String getUsername(String token){
        return validarToken(token).getPayload().getSubject();
    }

    private JwtParser getParser(){
        return Jwts.parser().setSigningKey("senha123")
                .build();
    }
}

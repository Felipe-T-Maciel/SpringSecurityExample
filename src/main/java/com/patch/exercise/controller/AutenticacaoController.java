package com.patch.exercise.controller;

import com.patch.exercise.model.entity.UsuarioLogin;
import com.patch.exercise.utils.CookieUtil;
import com.patch.exercise.utils.JwUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;
    private final CookieUtil cookieUtil = new CookieUtil();
    private final JwUtil jwUtil = new JwUtil();
    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody UsuarioLogin usuario, HttpServletRequest request, HttpServletResponse response){
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

//            SecurityContext context = SecurityContextHolder.createEmptyContext();
//            context.setAuthentication(authentication);
//            securityContextRepository.saveContext(context, request, response);

            UserDetails user = (UserDetails) authentication.getPrincipal();
            Cookie cookie = cookieUtil.gerarCookie(user);
            response.addCookie(cookie);
            return ResponseEntity.ok("Autenticação bem sucedida");

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
        }
    }
}

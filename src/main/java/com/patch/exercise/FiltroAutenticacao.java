package com.patch.exercise;

import com.patch.exercise.service.AutenticacaoSerivce;
import com.patch.exercise.utils.CookieUtil;
import com.patch.exercise.utils.JwUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class FiltroAutenticacao extends OncePerRequestFilter {

    private AutenticacaoSerivce userDetailsService;
    private final CookieUtil cookieUtil;
    private final JwUtil jwUtil;
    private SecurityContextRepository securityContextRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        Cookie cookie = cookieUtil.getCookie(request, "JWT");
        String token = cookie.getValue();
        String username = jwUtil.getUsername(token);
        UserDetails user = userDetailsService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextRepository.saveContext(context, request, response);

        filterChain.doFilter(request, response);
    }

    private boolean rotaPublica(HttpServletRequest request){
        return request.getRequestURI().equals("/login") && request.getMethod().equals("/POST");
    }
}

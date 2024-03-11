package com.patch.exercise;

import com.patch.exercise.utils.CookieUtil;
import com.patch.exercise.utils.JwUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class FiltroAutenticacao extends OncePerRequestFilter {
    private CookieUtil cookieUtil = new CookieUtil();
    private JwUtil jwUtil = new JwUtil();
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        Cookie cookie = cookieUtil.getCookie(request, "JWT");
        String token = cookie.getValue();
        jwUtil.validarToken(token);
        filterChain.doFilter(request, response);
    }
}

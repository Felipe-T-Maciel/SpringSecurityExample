package com.patch.exercise.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Locale;

@AllArgsConstructor
public enum Autorizacao implements GrantedAuthority {
    GET("get"),
    POST("Post"),
    PUT("Put"),
    DELETE("Delete");

    private final String nome;

    @Override
    public String getAuthority() {
        return name();
    }
}

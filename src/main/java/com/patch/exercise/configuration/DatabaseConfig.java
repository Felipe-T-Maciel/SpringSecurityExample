package com.patch.exercise.configuration;

import com.patch.exercise.model.entity.Autorizacao;
import com.patch.exercise.model.entity.UsuarioDetailsEntity;
import com.patch.exercise.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
@AllArgsConstructor
public class DatabaseConfig {
    private final UserRepository userRepository;
    @PostConstruct
    public void init(){
        com.patch.exercise.model.entity.User user = new com.patch.exercise.model.entity.User();
        user.setName("Teste");
        user.setUsuarioDetailsEntity(
                UsuarioDetailsEntity.builder()
                        .user(user)
                        .enabled(true)
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .username("teste@gmail.com")
                        .password(new BCryptPasswordEncoder().encode("123"))
                        .authorities(List.of(Autorizacao.GET))
                        .build()
        );
        userRepository.save(user);
    }
}

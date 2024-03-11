package com.patch.exercise.configuration;

import com.patch.exercise.service.AutenticacaoSerivce;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@AllArgsConstructor
public class BeansConfigs {

    private final AutenticacaoSerivce autenticacaoSerivce;

    @Bean
    public SecurityContextRepository securityContextRepository(){
        return new HttpSessionSecurityContextRepository();
    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return autenticacaoSerivce;
//    }

    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//        dao.setPasswordEncoder(passwordEncoder());
        dao.setUserDetailsService(autenticacaoSerivce);
        return new ProviderManager(dao);
    }

//    @Autowired
//    public  void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(autenticacaoSerivce)
//                .passwordEncoder(passwordEncoder());
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

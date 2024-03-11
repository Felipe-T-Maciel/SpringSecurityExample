package com.patch.exercise.service;

import com.patch.exercise.model.entity.User;
import com.patch.exercise.model.entity.UsuarioDetailsEntity;
import com.patch.exercise.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AutenticacaoSerivce implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsuarioDetailsEntity_Username(username);
        if(userOptional.isPresent()){
            return userOptional.get().getUsuarioDetailsEntity();
        }
        throw new UsernameNotFoundException("Dados invalidos");
    }
}

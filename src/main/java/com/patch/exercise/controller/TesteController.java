package com.patch.exercise.controller;

import com.patch.exercise.model.entity.User;
import com.patch.exercise.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/teste")
public class TesteController {

    private final UserRepository userRepository;

    @GetMapping
    public String teste(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        return "Teste " + auth.getName() + "!";
    }

    @GetMapping("/users")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @PostMapping
    public void cadastroUsuario(@RequestBody User user){
        userRepository.save(user);
    }
}

package com.lextereum.lextereumbackend.controller;

import com.lextereum.lextereumbackend.model.User;
import com.lextereum.lextereumbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") //TODO use docker
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createUser(@RequestBody User newUser) {
        userRepository.save(newUser);
        return userRepository.existsById(newUser.getId());
    }

}

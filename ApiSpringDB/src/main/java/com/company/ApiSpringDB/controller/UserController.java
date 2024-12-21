package com.company.ApiSpringDB.controller;

import com.company.ApiSpringDB.entity.User;
import com.company.ApiSpringDB.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<User> post(@RequestBody CreateUserDTO createUserDTO) {
        var userId = userService.createUser(createUserDTO);

        return ResponseEntity.created(URI.create("/v1/createUsr" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getById(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

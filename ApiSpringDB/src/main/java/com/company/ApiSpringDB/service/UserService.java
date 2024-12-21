package com.company.ApiSpringDB.service;

import com.company.ApiSpringDB.controller.CreateUserDTO;
import com.company.ApiSpringDB.entity.User;
import com.company.ApiSpringDB.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /*Serve para indicar que algo vai ser passado como parametro para ser armazenado
    * no repository, sem precisar indicar a maneira de ser feita*/

    public UUID createUser(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setName(createUserDTO.name());
        user.setEmail(createUserDTO.email());
        user.setPassword(createUserDTO.password());

        userRepository.save(user);

        return user.getUserId();
    }
}

package com.company.ApiSpringDB.service;

import com.company.ApiSpringDB.controller.CreateUserDTO;
import com.company.ApiSpringDB.controller.UpUserDTO;
import com.company.ApiSpringDB.entity.User;
import com.company.ApiSpringDB.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(String userId, UpUserDTO upUserDTO) {
        var id = UUID.fromString(userId); // userId em UUID
        var userEntity = userRepository.findById(id);

        if(userEntity.isPresent()) {
            var user = userEntity.get();

            if(upUserDTO.name() != null) { // Verifica se os campos tem valor
                user.setName(upUserDTO.name());
            }

            if(upUserDTO.password() != null) {
                user.setPassword(upUserDTO.password());
            }

            userRepository.save(user);
        }
    }

    public void deleteById(String userId) {
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(id);

        if(userExists) {
            userRepository.deleteById(id);
        }
    }
}

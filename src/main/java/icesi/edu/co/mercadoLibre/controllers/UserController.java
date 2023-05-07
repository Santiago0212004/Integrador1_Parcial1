package icesi.edu.co.mercadoLibre.controllers;

import icesi.edu.co.mercadoLibre.entities.User;
import icesi.edu.co.mercadoLibre.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "users/create",
            consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestBody User user){
        userRepository.save(user);

        return ResponseEntity.status(200).body("Usuario creado");
    }
}

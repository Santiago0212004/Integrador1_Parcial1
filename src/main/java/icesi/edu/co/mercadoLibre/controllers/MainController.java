package icesi.edu.co.mercadoLibre.controllers;

import icesi.edu.co.mercadoLibre.entities.Product;
import icesi.edu.co.mercadoLibre.entities.ProductUser;
import icesi.edu.co.mercadoLibre.entities.User;
import icesi.edu.co.mercadoLibre.repositories.ProductRepository;
import icesi.edu.co.mercadoLibre.repositories.ProductUserRepository;
import icesi.edu.co.mercadoLibre.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MainController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductUserRepository productUserRepository;

    @PostMapping(value = "users/create",
                consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestBody User user){
        userRepository.save(user);

       return ResponseEntity.status(200).body("Usuario creado");
    }

    @PostMapping(value =  "products/create",
                consumes = "application/json")
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        productRepository.save(product);

        return ResponseEntity.status(200).body("Producto creado");
    }

    @PutMapping(value = "users/addToCart")
    public ResponseEntity<?> addToCart(@RequestHeader Long userId, @RequestHeader Long productId){
        Optional<User> repositoryUser = userRepository.findById(userId);
        Optional<Product> repositoryProduct = productRepository.findById(productId);
        User user;
        Product product;
        if(repositoryUser.isPresent()){
            user = repositoryUser.get();
            if(repositoryProduct.isPresent()){
                product = repositoryProduct.get();
                ProductUser productUser = new ProductUser();
                productUser.setUser(user);
                productUser.setProduct(product);
                productUserRepository.save(productUser);
                return ResponseEntity.status(200).body("Producto añadido al carro de compras");
            }
            return ResponseEntity.status(400).body("El producto que buscas no existe");
        }
        return ResponseEntity.status(400).body("Usuario Inexistente");
    }

    /*
    @DeleteMapping(value = "users/delete")
    public ResponseEntity<?> deleteProduct(@RequestHeader Long userId, @RequestHeader Long productId){
        Optional<User> repositoryUser = userRepository.findById(userId);
        Optional<Product> repositoryProduct = productRepository.findById(productId);
        User user;
        Product product;
        if(repositoryUser.isPresent()){
            user = repositoryUser.get();
            if(repositoryProduct.isPresent()){
                product = repositoryProduct.get();

            }
            return ResponseEntity.status(400).body("El producto que buscas no existe");
        }
        return ResponseEntity.status(400).body("Usuario Inexistente");
    }
    */

}

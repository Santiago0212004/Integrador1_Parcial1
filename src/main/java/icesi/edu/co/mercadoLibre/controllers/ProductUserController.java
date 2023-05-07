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

import java.util.List;
import java.util.Optional;

@RestController
public class ProductUserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductUserRepository productUserRepository;


    @PostMapping(value = "users/addToCart")
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


    @DeleteMapping(value = "users/deleteProductUnitFromShoppingCart")
    public ResponseEntity<?> deleteProductUnit(@RequestHeader Long userId, @RequestHeader Long productId){
        Optional<User> repositoryUser = userRepository.findById(userId);
        Optional<Product> repositoryProduct = productRepository.findById(productId);
        if(repositoryUser.isPresent()){
            User user = repositoryUser.get();
            if(repositoryProduct.isPresent()){
                Product product = repositoryProduct.get();
                List<ProductUser> productUnitsOfUser = productUserRepository.getProductUnitsOfUser(userId,productId);
                if(productUnitsOfUser.size()>0){
                    productUserRepository.deleteById(productUnitsOfUser.get(0).getId());
                    return ResponseEntity.status(200).body("Unidad de: "+product.getName()+" borrada del carro de compras de "+user.getName());
                }else{
                    return ResponseEntity.status(400).body("El usuario: "+user.getName()+" no tiene unidades de: "+product.getName()+" para eliminar.");
                }
            }
            return ResponseEntity.status(400).body("El producto que buscas no existe");
        }
        return ResponseEntity.status(400).body("Usuario Inexistente");
    }

    @DeleteMapping(value = "users/deleteProductFromShoppingCart")
    public ResponseEntity<?> deleteProduct(@RequestHeader Long userId, @RequestHeader Long productId){
        Optional<User> repositoryUser = userRepository.findById(userId);
        Optional<Product> repositoryProduct = productRepository.findById(productId);
        if(repositoryUser.isPresent()){
            User user = repositoryUser.get();
            if(repositoryProduct.isPresent()){
                Product product = repositoryProduct.get();
                List<ProductUser> productUnitsOfUser = productUserRepository.getProductUnitsOfUser(userId,productId);
                if(productUnitsOfUser.size()>0){
                    while(productUnitsOfUser.size()>0){
                        productUserRepository.deleteById(productUnitsOfUser.get(0).getId());
                        productUnitsOfUser.remove(0);
                    }
                    return ResponseEntity.status(200).body("Todas las unidades de: "+product.getName()+" borrada del carro de compras de "+user.getName());
                }else{
                    return ResponseEntity.status(400).body("El usuario: "+user.getName()+" no tiene unidades de: "+product.getName()+" para eliminar.");
                }
            }
            return ResponseEntity.status(400).body("El producto que buscas no existe");
        }
        return ResponseEntity.status(400).body("Usuario Inexistente");
    }

    @GetMapping(value = "users/getTotalShoppingCart")
    public ResponseEntity<?> getTotalShoppingCart(@RequestHeader Long userId){
        Optional<User> repositoryUser = userRepository.findById(userId);
        if(repositoryUser.isPresent()){
            User user = repositoryUser.get();
            return ResponseEntity.status(200).body("Precio del carrito de: "+user.getName()+": "+productUserRepository.getShoppingCartPrice(userId));
        }
        return ResponseEntity.status(400).body("Usuario Inexistente");
    }

}

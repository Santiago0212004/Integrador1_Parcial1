package icesi.edu.co.mercadoLibre.controllers;

import icesi.edu.co.mercadoLibre.entities.Product;
import icesi.edu.co.mercadoLibre.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @PostMapping(value =  "products/create",
            consumes = "application/json")
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        productRepository.save(product);

        return ResponseEntity.status(200).body("Producto creado");
    }
}

package icesi.edu.co.mercadoLibre.repositories;

import icesi.edu.co.mercadoLibre.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}

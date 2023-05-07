package icesi.edu.co.mercadoLibre.repositories;

import icesi.edu.co.mercadoLibre.entities.ProductUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductUserRepository extends CrudRepository<ProductUser, Long> {
    @Query(value = "SELECT * FROM product_user WHERE user_id = :userId AND product_id = :productId", nativeQuery = true)
    List<ProductUser> getProductUnitsOfUser(@Param("userId") Long userId, @Param("productId") Long productId);

    @Query("SELECT SUM(p.price) FROM product_user pu JOIN pu.product p WHERE pu.user.id = :userId")
    Double getShoppingCartPrice(@Param("userId") Long userId);
}

package icesi.edu.co.mercadoLibre.repositories;

import icesi.edu.co.mercadoLibre.entities.ProductUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductUserRepository extends CrudRepository<ProductUser, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM product_user a WHERE a.user.id =: userId AND a.product.id =: productId")
    public void deleteProductOfShoppingCart(@Param("userId") Long userId, @Param("productId") Long productId);

    @Transactional
    @Modifying
    @Query("DELETE FROM product_user a WHERE a.user.id =: userId AND a.product.id =: productId")
    public void deleteUnitOfShoppingCart(@Param("userId") Long userId, @Param("productId") Long productId);

    @Query("SELECT SUM(p.price) FROM product_user pu JOIN pu.product p WHERE pu.user.id = :userId")
    Double getShoppingCartPrice(@Param("userId") Long userId);
}

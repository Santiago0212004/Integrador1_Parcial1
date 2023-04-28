package icesi.edu.co.mercadoLibre.repositories;

import icesi.edu.co.mercadoLibre.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}

package icesi.edu.co.mercadoLibre.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ProductUser> productUsers;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductUser> getProductUsers() {
        return productUsers;
    }

    public void setProductUsers(List<ProductUser> productUsers) {
        this.productUsers = productUsers;
    }
}

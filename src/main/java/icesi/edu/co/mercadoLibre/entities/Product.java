package icesi.edu.co.mercadoLibre.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Double price;

    @OneToMany(mappedBy = "product")
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<ProductUser> getProductUsers() {
        return productUsers;
    }

    public void setProductUsers(List<ProductUser> productUsers) {
        this.productUsers = productUsers;
    }
}

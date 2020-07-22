package com.myproject.entity.productdb;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_details")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    private String comment;

    public Long getProductID() {
        return productID;
    }

    public String getComment() {
        return comment;
    }

    public Product() {
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Product(Long productID,String comment){
        this.productID=productID;
        this.comment=comment;
    }
}

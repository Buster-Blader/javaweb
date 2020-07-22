package com.myproject.repositories;

import com.myproject.entity.productdb.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositiry extends JpaRepository<Product,Long> {

    @Query(value="select pro from Product pro where pro.productID=:id")
    Product findProductByProductID(@Param("id") Long id);

}

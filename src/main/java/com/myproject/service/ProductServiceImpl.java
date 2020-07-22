package com.myproject.service;

import com.myproject.entity.productdb.Product;
import com.myproject.repositories.ProductRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepositiry productRepositiry;

    @Override
    public List<Product> findAll() {
        return productRepositiry.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepositiry.findOne(id);
    }
}

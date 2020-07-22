package com.myproject.service;

import com.myproject.entity.productdb.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> findAll();
    Product findById(Long id);

}

package com.it.service;

import com.it.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll() throws Exception;
    void save(Product product) throws Exception;
    void delete(Integer id) throws Exception;
    void update(Product product) throws  Exception;
}

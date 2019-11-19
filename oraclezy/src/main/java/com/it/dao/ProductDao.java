package com.it.dao;

import com.it.domain.Product;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProductDao {
    List<Product> findAll() throws Exception;
    void save(Product product) throws Exception;
    void delete(Integer id) throws Exception;
    void update(Product product) throws  Exception;
}

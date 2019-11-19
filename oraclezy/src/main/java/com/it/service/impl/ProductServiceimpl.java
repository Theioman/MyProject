package com.it.service.impl;

import com.it.dao.ProductDao;
import com.it.domain.Product;
import com.it.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("productService")
public class ProductServiceimpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> findAll() throws Exception {
        List<Product> all = productDao.findAll();
        return all;
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

    @Override
    public void delete(Integer id) throws Exception {
        productDao.delete(id);
    }

    @Override
    public void update(Product product) throws Exception {
        productDao.update(product);
    }

}

package com.ivanyuyuk.dao;

import com.ivanyuyuk.entity.Product;
import com.ivanyuyuk.entity.ProductWarehouse;

import java.util.List;

public interface ProductDao {

    Product getProduct(Long id);

    void saveProduct(Product product);

    void deleteProduct(Long id);

    List<Product> getProducts();

    void update(Product product, Long id);
}

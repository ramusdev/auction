package com.rb.auction.database;

import com.rb.auction.model.Product;

import java.util.List;
import java.util.Optional;

public interface InterfaceProductDao {
    List<Product> getProducts();
    List<Product> getByName(String productName);
    int addProduct(Product product);
    Optional<Product> getProductById(int productId);
    void updateProduct(Product product);
    List<Product> getByUserId(int id);
}

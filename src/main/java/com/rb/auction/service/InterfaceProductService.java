package com.rb.auction.service;

import com.rb.auction.model.Product;

import java.util.List;

public interface InterfaceProductService {
    List<Product> getAllProducts();
    List<Product> getByName(String productName);
    int addProduct(Product product, int auctionId);
    Product getProductById(int productId);
    void updateProduct(Product product);
    List<Product> getProductsByUserId(int id);
}
